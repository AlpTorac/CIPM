package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationDropOperationGenerator {
	private static final String dropMethodNameTemplate = "drop";

	private static final String dropMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(dropMethodNameTemplate,
				FluentAPIConstants.getGenModelURL(), initType, dropMethodBodyTemplate);
	}
}
