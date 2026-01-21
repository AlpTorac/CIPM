package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationResetOperationGenerator {
	private static final String resetMethodDocumentation = "Resets this initialisation instance, which discards this.get"
			+ FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName()
			+ "(). Does not drop this initialisation instance from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "(), meaning that it will still be accessible from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "(). This initialisation instance can then be re-used by calling this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationNewElementMethodName() + "().";

	private static final String resetMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.set" + FluentAPISuperInitialisationConstants
					.getCapitalisedFluentAPISuperInitialisationCurrentElementReferenceName() + "(null)",
			//
			"return this");

	public EOperation generateResetInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationResetMethodNameTemplate(),
				initType, resetMethodBodyTemplate, resetMethodDocumentation);
	}
}
