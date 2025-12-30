package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIInitialisationResetOperationGenerator {
	private static final String resetMethodNameTemplate = "reset";
	private static final String resetMethodDocumentation = "Resets this initialisation instance, which discards this.getCurrentElement(). Does not drop this initialisation instance from this.toAPI(), meaning that it will still be accessible from this.toAPI(). This initialisation instance can then be re-used by calling this.newElement().";

	private static final String resetMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("this.setCurrentElement(null)",
			//
			"return this");

	public EOperation generateResetInitialisationMethod(EClass initType) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(resetMethodNameTemplate, initType,
				resetMethodBodyTemplate, resetMethodDocumentation);
	}
}
