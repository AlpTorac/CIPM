package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.AbstractInitialisationMethods;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPINewMethodGenerator {
	private static final String eClassParamName = "eObjEClass";
	private static final String topLevelNewMethodName = "newX";
	private static final String newMethodNameTemplate = "new%s";
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	public List<EOperation> getAllRootAPINewOperations(EClass rootAPICls, EClass initialisationSuperTypeEClass,
			List<EClass> initEClss, List<EClass> eObjEClss, FluentAPITargetMetamodelFeatureFilter filter) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelNewOperation(rootAPICls, initialisationSuperTypeEClass));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (FluentAPIGenerationUtil.hasModifiableFeatures(eObjEClass, filter)) {
				ops.add(getRootAPINewOperationForEClassWithModifiableFeats(rootAPICls, eObjEClass, initEClass));
			} else {
				ops.add(getRootAPINewOperationForEClassWithoutModifiableFeats(rootAPICls, eObjEClass));
			}

		}

		return ops;
	}

	public EOperation getRootAPITopLevelNewOperation(EClass rootAPICls, EClass initialisationSuperTypeEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(eClassParamName,
				FluentAPIGenerationUtil.getEClassEClass());
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(topLevelNewMethodName, genModelURL, initialisationSuperTypeEClass,
						"return " + FluentAPIMethodsUtil.callMethod(FluentEObjectAPIMethods.class,
								"getInitialisationForX", FluentAPIMethodsUtil.getThisArgument(), param.getName()) + ";",
						param);
	}

	public EOperation getRootAPINewOperationForEClassWithModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), genModelURL,
				initECls,
				FluentAPIMethodsUtil.callMethodAndReturn(FluentEObjectAPIMethods.class, "newElement",
						FluentAPIMethodsUtil.getThisArgument(),
						FluentAPIMethodsUtil.getClassLiteral(eObjEClass.getInstanceClass()),
						FluentAPIMethodsUtil.callMethod(FluentEObjectAPIMethods.class, newMethodNameTemplate)
				// TODO continue by adding initialisation as return value
				)

		);
	}

	public EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass rootAPICls, EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), genModelURL,
				eObjEClass,
				FluentAPIMethodsUtil.callMethodAndReturn(
						FluentAPIMethodsUtil.callMethod(FluentEObjectAPIMethods.class, "newElement",
								FluentAPIMethodsUtil.getThisArgument(),
								FluentAPIMethodsUtil.getClassLiteral(eObjEClass.getInstanceClass())),

						FluentAPIMethodsUtil.castTo(eObjEClass.getInstanceClass().getName(),
								FluentAPIMethodsUtil.callMethod(AbstractInitialisationMethods.class,
										"getCurrentElement",
										FluentAPIMethodsUtil.callMethod(FluentEObjectAPIMethods.class,
												"getInitialisationForX", FluentAPIMethodsUtil.getThisArgument(),
												FluentAPIMethodsUtil
														.getClassLiteral(eObjEClass.getInstanceClass()))))));
	}
}
