package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationNewElementMethodGenerator {
	private static final String newElementMethodName = "newElement";
	private static final String newElementOperationDocumentation = "Creates a minimal EObject instance, without modifying any of its features, and sets it as the current element (i.e. return value of this.getCurrentElement()) in concrete initialisation classes. Does nothing in this class.";

	private static final String newElementMethodBody = FluentAPIMethodsUtil.joinLOC("return this");

	public EOperation generateNewElementMethod(EClass initSuperEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(newElementMethodName, initSuperEClass,
				newElementMethodBody);
	}
}
