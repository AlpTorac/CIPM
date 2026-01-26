package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIModifyElementMethodGenerator {
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
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMarkedMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op,
				String.format(modifyMarkedElementMethodBodyTemplate,
						FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls),
						eObjEClass.getInstanceClass().getSimpleName()));
		FluentAPIGenerationUtil.addEParameters(op, markKeyParam);
		return op;
	}

	public EOperation getRootAPIModifyElementOperationForEClass(EClass rootAPICls, EClass eObjEClass, EClass initECls) {
		var param = getEObjectParam(eObjEClass);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyMethodNameForType(eObjEClass), initECls);
		FluentAPIGenerationUtil.addBody(op, String.format(modifyElementMethodBodyTemplate,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initECls)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	public EOperation getRootAPITopLevelModifyElementOperation(EClass rootAPICls,
			EClass initialisationSuperTypeEClass) {
		var param = getEObjectParam(EcorePackage.Literals.EOBJECT);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIModifyXMethodName(), initialisationSuperTypeEClass);
		FluentAPIGenerationUtil.addBody(op, String.format(topLevelModifyElementMethodBody,
				FluentAPIGenerationUtil.getFullyQualifiedEClassName(initialisationSuperTypeEClass)));
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
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
