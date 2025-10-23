package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

public class FluentAPINewElementMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String newElementMethodName = "newElement";

	public EOperation generateNewElementMethod(EClass initEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(newElementMethodName, genModelURL, initEClass, null);
	}
}
