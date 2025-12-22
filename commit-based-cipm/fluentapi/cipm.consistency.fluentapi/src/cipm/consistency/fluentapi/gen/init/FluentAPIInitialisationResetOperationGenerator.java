package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationResetOperationGenerator {
	private static final String resetMethodNameTemplate = "reset";

	private static final String resetMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("this.setCurrentElement(null)",
			//
			"return this");

	public EOperation generateResetInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(resetMethodNameTemplate,
				FluentAPIConstants.getGenModelURL(), initType, resetMethodBodyTemplate);
	}
}
