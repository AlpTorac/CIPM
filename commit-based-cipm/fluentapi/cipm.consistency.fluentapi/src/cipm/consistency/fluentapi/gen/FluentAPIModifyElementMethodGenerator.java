package cipm.consistency.fluentapi.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIModifyElementMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String modifyElementMethodNameTemplate = "modify%s";

	private static final String modifyElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation type class name
			// %s: EObject param name
			"return (%s)" + "this.getInitialisationForX(%s)");

	private static final String modifyElementEObjectParamName = "eobjToModify";

	public List<EOperation> getAllRootAPIModifyElementOperations(EClass rootAPICls,
			EClass initialisationSuperTypeEClass, List<EClass> initEClss, List<EClass> eObjEClss) {
		var ops = new ArrayList<EOperation>();

		for (int i = 0; i < eObjEClss.size(); i++) {
			var eObjEClass = eObjEClss.get(i);
			var initEClass = initEClss.get(i);
			ops.add(getRootAPIModifyElementOperationForEClass(rootAPICls, eObjEClass, initEClass));
		}

		return ops;
	}

	public EOperation getRootAPIModifyElementOperationForEClass(EClass rootAPICls, EClass eObjEClass, EClass initECls) {
		var param = getEObjectParam(eObjEClass);
		return FluentAPIGenerationUtil.generateEOperationWithBody(
				String.format(modifyElementMethodNameTemplate, eObjEClass.getInstanceClass().getSimpleName()),
				genModelURL, initECls, String.format(modifyElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls), param.getName()),
				param);
	}

	public EParameter getEObjectParam(EClass eObjEClass) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(modifyElementEObjectParamName, eObjEClass);
	}
}
