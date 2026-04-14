package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIModifyElementMethodGenerator {
	// TODO Add documentation

	private static final String topLevelModifyElementMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified AbstractInitialisation class name
			"return (%s) this." + FluentAPIRootAPIConstants.getFluentAPIRootAPIGetInitialisationForMethodName() + "("
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
			EClass initialisationSuperTypeEClass, List<EClass> initEClss, List<EClass> eObjEClss,
			FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelModifyElementOperation(rootAPICls, initialisationSuperTypeEClass, targetMetamodelPackageProvider));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			ops.add(getRootAPIModifyElementOperationForEClass(rootAPICls, eObjEClass, initEClass, targetMetamodelPackageProvider));
			ops.add(getRootAPIModifyMarkedElementOperationForEClass(rootAPICls, eObjEClass, initEClass, targetMetamodelPackageProvider));
		}

		return ops;
	}

	public EOperation getRootAPIModifyMarkedElementOperationForEClass(EClass rootAPICls, EClass eObjEClass,
			EClass initECls, FluentAPITargetMetamodelPackageProvider targetMetamodelPackageProvider) {
		var markKeyParam = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameForType(eObjEClass), initECls,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						targetMetamodelPackageProvider.getSimpleClassNameFor(eObjEClass)),
				markKeyParam);
	}

	public EOperation getRootAPIModifyElementOperationForEClass(EClass rootAPICls, EClass eObjEClass, EClass initECls, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getEObjectParam(eObjEClass);
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameForType(eObjEClass), initECls,
				String.format(modifyElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)),
				param);
	}

	public EOperation getRootAPITopLevelModifyElementOperation(EClass rootAPICls,
			EClass initialisationSuperTypeEClass, FluentAPITargetMetamodelPackageProvider provider) {
		var param = getEObjectParam(EcorePackage.Literals.EOBJECT);
		return FluentAPIGenerationUtil
				.generateEOperationWithBody(FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyXMethodName(),
						initialisationSuperTypeEClass,
						String.format(topLevelModifyElementMethodBody,
								FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)),
						param);
	}

	public EParameter getEObjectParam(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodEObjectParameterName(), eObjEClass);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
