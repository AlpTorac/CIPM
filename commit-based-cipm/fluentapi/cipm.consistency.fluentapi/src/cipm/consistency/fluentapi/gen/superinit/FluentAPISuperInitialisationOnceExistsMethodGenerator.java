package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {

	private static final String onceExistsMethodMarkedKeyBody = FluentAPIMethodsUtil
			.joinLOC(FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists(%s, ("
			// %s: Mark key parameter name
					+ FluentAPIRootAPIConstants.getModelConstructionTaskClass().getName() + ") "
					+ FluentAPISuperInitialisationConstants
							.getFluentAPISuperInitialisationOnceExistsRunnableParameterName()
					+ ")", "return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGenerator = (keyParam) -> {
			var taskParam = getTaskParam(context);
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), context.getInitSuperECls());
			FluentAPIGenerationUtil.addBody(op, String.format(onceExistsMethodMarkedKeyBody, keyParam.getName()));
			FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
			return op;
		};

		ops.add(opGenerator.apply(getMarkKeyParam()));
		ops.add(opGenerator.apply(getMarkKeyListParam()));
		ops.add(opGenerator.apply(getMarkKeyArrayParam(context)));
		return ops;
	}

	private EParameter getMarkKeyParam() {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIMarkKeyParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getMarkKeyListParam() {
		var param = FluentAPIGenerationUtil.generateManyValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getMarkKeyArrayParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateArrayValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyListParameterName(),
				EcorePackage.Literals.EJAVA_OBJECT);
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMarkKeyDocumentation());
		return param;
	}

	private EParameter getTaskParam(FluentAPIGenerationContext context) {
		var param = FluentAPIGenerationUtil.generateSingleValuedEParameter(context,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterName(),
				FluentAPIRootAPIConstants.getModelConstructionTaskClass());
		FluentAPIGenerationUtil.addDocumentation(param,
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsRunnableParameterDocumentation());
		return param;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
