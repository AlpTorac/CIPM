package cipm.consistency.fluentapi.gen.rootapi;

import java.util.Map;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGeneralParameterGenerator;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.mark.FluentAPIOnceExistsExtension;

public class FluentAPIRootAPIOnceExistsMethodGenerator implements IFluentAPIMethodGenerator {
	// TODO Add documentation

	private static final String onceExistsMethodBody = FluentAPIMethodsUtil
			.joinLOC(
					FluentAPIOnceExistsExtension.class.getName() + ".addOnceExists("
							+ ModelConstants.GeneralParameters.MARK_KEY_PARAMETER_NAME.get() + ", "
							+ ModelConstants.GeneralParameters.ONCE_EXISTS_TASK_PARAMETER_NAME.get() + ")",
					"return this");

	public EOperation generateAllOnceExistsMethods(FluentAPIGenerationContext context) {
		var taskParam = FluentAPIGeneralParameterGenerator.getOnceExistsTaskParam(context);
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.OnceExists.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, onceExistsMethodBody);
		FluentAPIGenerationUtil.addDocumentation(op, ModelConstants.FluentAPI.OnceExists.DOC.get());
		FluentAPIGenerationUtil.addEParameters(op, FluentAPIGeneralParameterGenerator.getMarkKeyParam(), taskParam);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(ModelConstants.FluentAPI.OnceExists.NAME.get(),
				ModelConstants.FluentAPI.OnceExists.SUMMARY.get());
	}
}
