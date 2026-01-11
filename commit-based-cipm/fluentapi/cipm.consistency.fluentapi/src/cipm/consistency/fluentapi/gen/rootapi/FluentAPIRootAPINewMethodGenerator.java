package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPINewMethodGenerator {
	// TODO Add documentation

	private static final String eClassParamName = "eObjEClass";

	private static final String topLevelNewMethodName = "newX";

	private static final String newMethodNameTemplate = "new%s";

	private static final String newXMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation super type class name
			// %s: EClass param name
			"return (%s)" + "this.getInitialisationForX(%s.getInstanceClass())");

	private static final String newXWithModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) this.getInitialisationForX(%s.class)");

	private static final String newXWithoutModifiableFeatsMethodBodyTemplate = FluentAPIMethodsUtil
			.joinLOC("return (%s) ((%s) this.getInitialisationForX(%s.class)).createNow()");

	public List<EOperation> getAllRootAPINewOperations(EClass rootAPICls, EClass initialisationSuperTypeEClass,
			List<EClass> initEClss, List<EClass> eObjEClss, FluentAPITargetMetamodelFeatureFilter filter) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelNewOperation(rootAPICls, initialisationSuperTypeEClass));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			if (filter.hasModifiableFeatures(eObjEClass)) {
				ops.add(getRootAPINewOperationForEClassWithModifiableFeats(rootAPICls, eObjEClass, initEClass));
			} else {
				ops.add(getRootAPINewOperationForEClassWithoutModifiableFeats(rootAPICls, eObjEClass, initEClass));
			}

		}

		return ops;
	}

	public EOperation getRootAPITopLevelNewOperation(EClass rootAPICls, EClass initialisationSuperTypeEClass) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(eClassParamName,
				FluentAPIGenerationUtil.getEClassEClass());
		return FluentAPIGenerationUtil.generateEOperationWithBody(topLevelNewMethodName, initialisationSuperTypeEClass,
				String.format(newXMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass),
						param.getName()),
				param);
	}

	public EOperation getRootAPINewOperationForEClassWithModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), initECls,
				String.format(newXWithModifiableFeatsMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName())

		);
	}

	public EOperation getRootAPINewOperationForEClassWithoutModifiableFeats(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(newMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), eObjEClass,
				String.format(newXWithoutModifiableFeatsMethodBodyTemplate, eObjEClass.getInstanceClass().getName(),
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getName()));
	}
}
