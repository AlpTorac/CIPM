package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationDropOperationGenerator {
	private static final String dropMethodNameTemplate = "drop";
	private static final String dropMethodDocumentation = "Removes this initialisation instance from this.toAPI(), meaning that it will no longer be accessible from this.toAPI().";

	private static final String dropMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(dropMethodNameTemplate, initType,
				dropMethodBodyTemplate, dropMethodDocumentation);
	}
}
