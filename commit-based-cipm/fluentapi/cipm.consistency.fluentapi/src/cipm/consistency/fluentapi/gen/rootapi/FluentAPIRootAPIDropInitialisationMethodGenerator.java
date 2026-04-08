package cipm.consistency.fluentapi.gen.rootapi;

import java.util.Map;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIDropInitialisationMethodGenerator implements IFluentAPIMethodGenerator {
	// TODO Add documentation

	private static final String dropInitialisationMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentAPIInitialisationStorage.class.getName() + ".dropOngoingInitialisation("
					+ ModelConstants.FluentAPI.DropInitialisation.INITIALISATION_PARAMETER_NAME.get() + ")",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(FluentAPIGenerationContext context) {
		var param = getInitialisationParam(context);
		var op = FluentAPIGenerationUtil.generateEOperation(ModelConstants.FluentAPI.DropInitialisation.NAME.get(),
				context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, dropInitialisationMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	private EParameter getInitialisationParam(FluentAPIGenerationContext context) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				ModelConstants.FluentAPI.DropInitialisation.INITIALISATION_PARAMETER_NAME.get(),
				context.getInitSuperECls());
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(ModelConstants.FluentAPI.DropInitialisation.NAME.get(),
				ModelConstants.FluentAPI.DropInitialisation.SUMMARY.get());
	}
}
