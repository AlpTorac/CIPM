package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
					+ FluentAPIGeneralParameterGenerator.getFluentAPIMarkKeyParameterName() + ", "
					+ FluentAPIGeneralParameterGenerator.getFluentAPIOnceExistsRunnableParameterName() + ")",
			"return this");

	public EOperation generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var taskParam = FluentAPIGeneralParameterGenerator.getRunnableParam(context);
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.RootAPI.OnceExists.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, FluentAPIGeneralParameterGenerator.getMarkKeyParam(), taskParam);
		return op;
	}
}
