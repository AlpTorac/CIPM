package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIInitialisationStorage;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIRootAPIClearAllOngoingInitialisationsMethodGenerator {
	private static final String clearAllOngoingInitsMethodBody = FluentAPIMethodsUtil
			.joinLOC(FluentAPIInitialisationStorage.class.getName()
					+ ModelConstants.FluentAPI.ClearAllOngoingInitialisations.NAME.call(), "return this");

	public EOperation generateClearAllOngoingInitsMethod(FluentAPIGenerationContext context) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				ModelConstants.FluentAPI.ClearAllOngoingInitialisations.NAME.get(), context.getFluentAPIECls());
		FluentAPIGenerationUtil.addBody(op, clearAllOngoingInitsMethodBody);
		return op;
	}
}
