package cipm.consistency.fluentapi.gen.superinit;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIDocumentationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.IFluentAPIMethodGenerator;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationResetOperationGenerator implements IFluentAPIMethodGenerator {
	private static final String resetMethodSummary = "Removes the current object under construction from this.";
	private static final String resetMethodDocumentation = FluentAPIDocumentationUtil
			.appendSummaryToStart(resetMethodSummary) + "Resets this "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance, which discards this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(). Does not drop this " + FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "(), meaning that it will still be accessible from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "(). This "
			+ FluentAPIInitialisationConstants.getFluentAPIInitialisationClassNameSuffix()
			+ " instance can then be re-used by calling this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodName() + "().";

	private static final String resetMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.set" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "(null)",
			//
			"return this");

	public EOperation generateResetInitialisationMethod(EClass initType) {
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationResetMethodNameTemplate(),
				initType);
		FluentAPIGenerationUtil.addBody(op, resetMethodBodyTemplate);
		FluentAPIGenerationUtil.addDocumentation(op, resetMethodDocumentation);
		return op;
	}

	@Override
	public Map<String, String> getMethodNamesToDescriptions() {
		return Map.of(FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationResetMethodNameTemplate(),
				resetMethodSummary);
	}
}
