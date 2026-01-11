package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIModifyElementMethodGenerator {
	// TODO Add documentation

	private static final String markKeyParameterName = "markKey";

	private static final String topLevelModifyElementMethodName = "modifyX";
	private static final String topLevelModifyElementMethodBody = FluentAPIMethodsUtil.joinLOC(
			// %s: Fully qualified AbstractInitialisation class name
			// %s: EObject param name
			"return (%s) this.getInitialisationForX(%s)");

	private static final String modifyElementMethodNameTemplate = "modify%s";

	private static final String modifyElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: EObject param name
			"return (%s) this.getInitialisationForX(%s)");

	private static final String modifyMarkedElementMethodNameTemplate = "modifyMarked%s";

	private static final String modifyMarkedElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: Modified class name
			// %s: Mark key
			"return (%s) this.getInitialisationForX(this.getMarked%s(%s))");

	private static final String modifyElementEObjectParamName = "eobjToModify";

	public List<EOperation> getAllRootAPIModifyElementOperations(EClass rootAPICls,
			EClass initialisationSuperTypeEClass, List<EClass> initEClss, List<EClass> eObjEClss) {
		var ops = new ArrayList<EOperation>();
		ops.add(getRootAPITopLevelModifyElementOperation(rootAPICls, initialisationSuperTypeEClass));

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			ops.add(getRootAPIModifyElementOperationForEClass(rootAPICls, eObjEClass, initEClass));
			ops.add(getRootAPIModifyMarkedElementOperationForEClass(rootAPICls, eObjEClass, initEClass));
		}

		return ops;
	}

	public EOperation getRootAPIModifyMarkedElementOperationForEClass(EClass rootAPICls, EClass eObjEClass,
			EClass initECls) {
		var markKeyParam = getMarkKeyParam();
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(modifyMarkedElementMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()),
				initECls,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getSimpleName(), markKeyParam.getName()),
				markKeyParam);
	}

	public EOperation getRootAPIModifyElementOperationForEClass(EClass rootAPICls, EClass eObjEClass, EClass initECls) {
		var param = getEObjectParam(eObjEClass);
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(modifyElementMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()), initECls,
				String.format(modifyElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls), param.getName()),
				param);
	}

	public EOperation getRootAPITopLevelModifyElementOperation(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = getEObjectParam(FluentAPIGenerationUtil.getEObjectEClass());
		return FluentAPIGenerationUtil.generateEOperationWithBody(topLevelModifyElementMethodName,
				initialisationSuperTypeEClass,
				String.format(topLevelModifyElementMethodBody,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass),
						param.getName()),
				param);
	}

	public EParameter getEObjectParam(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(modifyElementEObjectParamName, eObjEClass);
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(markKeyParameterName,
				EcorePackage.Literals.EJAVA_OBJECT);
	}
}
