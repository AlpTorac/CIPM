package cipm.consistency.fluentapi.gen.superinit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;
import cipm.consistency.fluentapi.gen.rootapi.FluentAPIRootAPIConstants;

public class FluentAPISuperInitialisationOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {

	private static final String onceExistsMethodMarkedKeyBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();
		Function<EParameter, EOperation> opGenerator = (keyParam) -> {
			var taskParam = FluentAPIGeneralParameterGenerator.getRunnableParam(context);
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), context.getInitSuperECls());
			FluentAPIGenerationUtil.addBody(op, onceExistsMethodMarkedKeyBody);
			FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
			FluentAPIGenerationUtil.addDocumentation(op,
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodDocumentation());
			return op;
		};

		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyParam()));
		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyColParam(context)));
		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyArrayParam(context)));
		return ops;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(),
				FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodSummary());
	}
}
