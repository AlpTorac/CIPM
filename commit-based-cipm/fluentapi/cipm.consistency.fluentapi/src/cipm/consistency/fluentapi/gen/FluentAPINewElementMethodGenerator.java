package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPINewElementMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newElementMethodName = "newElement";

	private static final String newElementMethodBody = FluentAPIMethodsUtil.joinLOC("return this");

	public EOperation generateNewElementMethod(EClass initSuperEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(newElementMethodName, genModelURL, initSuperEClass,
				newElementMethodBody);
	}
}
