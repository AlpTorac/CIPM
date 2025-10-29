package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationResetOperationGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String resetMethodNameTemplate = "reset";

	private static final String resetMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("this.setCurrentElement(null)",
			//
			"return this");

	public EOperation generateResetInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(resetMethodNameTemplate, genModelURL, initType,
				resetMethodBodyTemplate);
	}
}
