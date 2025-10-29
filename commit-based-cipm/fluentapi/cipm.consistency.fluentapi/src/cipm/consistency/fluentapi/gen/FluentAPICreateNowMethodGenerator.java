package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPICreateNowMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String createNowMethodName = "createNow";

	private static final String createNowMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return (%s) this.getCurrentElement()");

	public EOperation generateCreateNowMethod(EClass elemToInit) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(createNowMethodName, genModelURL, elemToInit,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()));
	}
}
