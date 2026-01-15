package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPICreateNowMethodGenerator {
	private static final String createNowMethodName = "createNow";
	private static final String createNowMethodDocumentation = "Finalises the initialisation and returns this.getCurrentElement(). Drops this initialisation instance from this.toAPI(), meaning that this initialisation instance will no longer be accessible from this.toAPI().";

	private static final String createNowMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			"this.toAPI().dropInitialisation(this)",
			//
			"return (%s) this.getCurrentElement()");

	public EOperation generateCreateNowMethod(EClass elemToInit) {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(createNowMethodName, elemToInit,
				String.format(createNowMethodBodyTemplate, elemToInit.getInstanceClass().getName()),
				createNowMethodDocumentation);
	}

	public static String getCreateNowMethodName() {
		return createNowMethodName;
	}
}
