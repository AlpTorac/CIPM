package cipm.consistency.fluentapi.gen.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.superinit.FluentAPISuperInitialisationConstants;

public class FluentAPIInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {
	private static final String onceExistsMethodMarkedKeyBodyTemplate = FluentAPIMethodsUtil
			.joinLOC(FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(%s, " // %s: Mark key parameter name
					+ FluentAPISuperInitialisationConstants
							.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
					+ ")", "return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context, EClass initECls) {
		var ops = new ArrayList<EOperation>();
		ops.add(generateOnceExistsMethodForSingleKey(context, initECls));
		ops.addAll(generateOnceExistsMethodsForMultipleKeys(context, initECls));
		return ops;
	}

	private EOperation generateOnceExistsMethodForSingleKey(FluentAPIGenerationContext context, EClass initECls) {
		var keyParam = getMarkKeyParam();
		var taskParam = getTaskParam(context);

		var op = FluentAPIGenerationUtil
				.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls);
		FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
		FluentAPIGenerationUtil.addBody(op, String.format(onceExistsMethodMarkedKeyBodyTemplate,
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationMarkKeyParameterName()));
		FluentAPIGenerationUtil.addDocumentation(op,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
		return op;
	}

	private List<EOperation> generateOnceExistsMethodsForMultipleKeys(FluentAPIGenerationContext context,
			EClass initECls) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGenerator = (p) -> {
			var taskParam = getTaskParam(context);
			var op = FluentAPIGenerationUtil
					.generateEOperation(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), initECls);
			FluentAPIGenerationUtil.addBody(op,
					String.format(onceExistsMethodMarkedKeyBodyTemplate, FluentAPISuperInitialisationConstants
							.getFluentAPISuperInitialisationOnceExistsMarkKeyListParameterName()));
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
			FluentAPIGenerationUtil.addEParameters(op, p, taskParam);
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
		return param;
	}

	private EParameter getTaskParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(),
				FluentAPIRootAPIConstants.getModelConstructionTaskClass());
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
