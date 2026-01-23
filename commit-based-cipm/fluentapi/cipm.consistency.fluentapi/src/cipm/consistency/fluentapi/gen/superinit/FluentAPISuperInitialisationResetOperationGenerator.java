package cipm.consistency.fluentapi.gen.superinit;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.init.FluentAPIInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationResetOperationGenerator {
	private static final String resetMethodDocumentation = "Resets this "
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
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationResetMethodNameTemplate(),
				initType, resetMethodBodyTemplate, resetMethodDocumentation);
	}
}
