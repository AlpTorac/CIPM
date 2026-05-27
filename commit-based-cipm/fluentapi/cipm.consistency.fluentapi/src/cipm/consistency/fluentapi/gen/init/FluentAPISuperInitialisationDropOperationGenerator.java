package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPISuperInitialisationConstants;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationDropOperationGenerator {
	private static final String dropMethodDocumentation = "Removes this initialisation instance from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName()
			+ "(), meaning that it will no longer be accessible from this."
			+ FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "().";

	private static final String dropMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this." + FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationToAPIMethodName() + "()."
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationMethodName() + "(this)",
			"return this");

	public EOperation generateDropInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(
				FluentAPISuperInitialisationConstants.getFluentAPISuperInitialisationDropMethodName(), initType,
				dropMethodBodyTemplate, dropMethodDocumentation);
	}
}
