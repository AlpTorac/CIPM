package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIRootAPIModifyElementMethodGenerator {
	// TODO Add documentation

	private static final String topLevelModifyMarkedElementMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (" + FluentAPIRootAPIConstants.getFluentAPIRootPackageName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName() + ") this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "(this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedMethodName() + "("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + "))");

	private static final String topLevelModifyElementMethodBody = FluentAPIMethodsUtil
			.joinLOC("return (" + FluentAPIRootAPIConstants.getFluentAPIRootPackageName() + "."
					+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationClassName() + ") this."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName() + ")");

	private static final String modifyElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			"return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName() + ")");

	private static final String modifyMarkedElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: Modified class name
			"return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName()
					+ "(this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetMarkedXMethodNameTemplate() + "("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + "))");

	public List<EOperation> getAllRootAPIModifyElementOperations(EClass rootAPICls,
			EClass initialisationSuperTypeEClass, List<EClass> initEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var eObjEClss = targetMetamodelPackageProvider.getAllTargetMetamodelConcreteEClasses();
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelModifyElementOperation(rootAPICls, initialisationSuperTypeEClass));
		ops.add(getRootAPITopLevelModifyMarkedElementOperation(rootAPICls, initialisationSuperTypeEClass));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			ops.add(getRootAPIModifyElementOperationForEClass(rootAPICls, eObjEClass, initEClass));
			ops.add(getRootAPIModifyMarkedElementOperationForEClass(rootAPICls, eObjEClass, initEClass));
		}

		return ops;
	}

	private EOperation getRootAPIModifyMarkedElementOperationForEClass(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		var markKeyParam = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getSimpleName()));
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam);
		return op;
	}

	private EOperation getRootAPIModifyElementOperationForEClass(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		var param = getEObjectParam(eObjEClass);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyElementOperation(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = getEObjectParam(EcorePackage.Literals.EOBJECT);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyXMethodName(), initialisationSuperTypeEClass);
		FluentAPIGenerationUtil.addBody(op, topLevelModifyElementMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EOperation getRootAPITopLevelModifyMarkedElementOperation(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = getMarkKeyParam();
		var op = FluentAPIGenerationUtil.generateEOperation(
				String.format(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameTemplate(),
						FluentAPIConstants.getDocumentationPlaceholder()),
				initialisationSuperTypeEClass);
		FluentAPIGenerationUtil.addBody(op, topLevelModifyMarkedElementMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EParameter getEObjectParam(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName(), eObjEClass);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
