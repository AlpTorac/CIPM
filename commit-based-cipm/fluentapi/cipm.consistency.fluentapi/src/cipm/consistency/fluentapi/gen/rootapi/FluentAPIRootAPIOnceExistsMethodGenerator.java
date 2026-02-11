package cipm.consistency.fluentapi.gen.rootapi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
							+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
							+ FluentAPIGeneralParameterGenerator.getFluentAPIOnceExistsRunnableParameterName() + ")",
					"return this");

	public List<EOperation> generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var ops = new ArrayList<EOperation>();

		Function<EParameter, EOperation> opGenerator = (keyParam) -> {
			var taskParam = FluentAPIGeneralParameterGenerator.getRunnableParam(context);
			var op = FluentAPIGenerationUtil.generateEOperation(
					FluentAPIRootAPIConstants.getFluentAPIRootAPIOnceExistsMethodName(), context.getFluentAPIECls());
			FluentAPIGenerationUtil.addBody(op, onceExistsMethodBody);
			FluentAPIGenerationUtil.addEParameters(op, keyParam, taskParam);
			return op;
		};

		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyParam()));
		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyColParam(context)));
		ops.add(opGenerator.apply(FluentAPIGeneralParameterGenerator.getMarkKeyArrayParam(context)));
		return ops;
	}
}
