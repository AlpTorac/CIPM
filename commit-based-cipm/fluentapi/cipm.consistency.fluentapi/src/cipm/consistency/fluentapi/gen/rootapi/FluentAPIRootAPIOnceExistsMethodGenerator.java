package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodSingleMarkKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	private static final String onceExistsMethodMultipleKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName()
					+ ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	public List<EOperation> generateAllOnceExistsMethods(EClass rootAPIECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethod(rootAPIECls));
		ops.add(generateOnceExistsListMethod(rootAPIECls));
		ops.add(generateOnceExistsArrayMethod(rootAPIECls));
		return ops;
	}

	private EOperation generateOnceExistsMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyParam();
		var taskParam = getRunnableParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodSingleMarkKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		return op;
	}

	private EOperation generateOnceExistsListMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyListParam();
		var taskParam = getRunnableParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodMultipleKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		return op;
	}

	private EOperation generateOnceExistsArrayMethod(EClass rootAPIECls) {
		var keyParam = getMarkKeyArrayParam();
		var taskParam = getRunnableParam();
		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), rootAPIECls);
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodMultipleKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		return op;
	}

	private EParameter getMarkKeyParam() {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyListParam() {
		return FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
	}

	private EParameter getMarkKeyArrayParam() {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		// TODO Add documentation
		return param;
	}

	private EParameter getRunnableParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(),
				FluentAPIRootAPIConstants.getModelConstructionTaskClass());
		// TODO Add documentation
		return param;
	}
}
