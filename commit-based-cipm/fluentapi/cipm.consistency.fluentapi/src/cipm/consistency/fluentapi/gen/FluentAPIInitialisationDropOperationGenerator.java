package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationDropOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String dropMethodNameTemplate = "drop";

	private static final String dropMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(dropMethodNameTemplate, genModelURL, initType,
				dropMethodBodyTemplate);
	}
}
