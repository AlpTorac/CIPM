package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodSingleMarkKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName() + ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	private static final String onceExistsMethodMultipleKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName()
					+ ", (java.lang.Runnable) "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethodSingleKey(context));
		ops.addAll(generateOnceExistsMethodsForMultipleKeys(context));
		return ops;
	}

	private EOperation generateOnceExistsMethodSingleKey(FluentAPIGenerationContext context) {
		var keyParam = getMarkKeyParam();
		var taskParam = getRunnableParam(context);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodSingleMarkKeyBody);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		return op;
	}

	private List<EOperation> generateOnceExistsMethodsForMultipleKeys(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGenerator = (keyParam) -> {
			var taskParam = getRunnableParam(context);
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, onceExistsMethodMultipleKeyBody);
			FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
			return op;
		};

		ops.add(opGenerator.apply(getMarkKeyListParam()));
		ops.add(opGenerator.apply(getMarkKeyArrayParam(context)));

		return ops;
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

	private EParameter getMarkKeyArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		// TODO Add documentation
		return param;
	}

	private EParameter getRunnableParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(),
				FluentAPIRootAPIConstants.getModelConstructionTaskClass());
		// TODO Add documentation
		return param;
	}
}
