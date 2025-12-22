package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPINewElementMethodGenerator {
	private static final String newElementMethodName = "newElement";

	private static final String newElementMethodBody = FluentAPIMethodsUtil.joinLOC("return this");

	public EOperation generateNewElementMethod(EClass initSuperEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(newElementMethodName,
				FluentAPIConstants.getGenModelURL(), initSuperEClass, newElementMethodBody);
	}
}
