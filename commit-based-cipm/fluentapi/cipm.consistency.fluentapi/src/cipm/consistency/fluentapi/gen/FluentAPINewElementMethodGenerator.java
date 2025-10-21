package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.AbstractInitialisationMethods;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPINewElementMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newElementMethodName = "newElement";

	private static final String newElementMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			//
			AbstractInitialisationMethods.class.getName() + ".newElement(this)",
			//
			"return this");

	public EOperation generateNewElementMethod(EClass initEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(newElementMethodName, genModelURL, initEClass,
				newElementMethodBodyTemplate);
	}
}
