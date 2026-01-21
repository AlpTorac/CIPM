package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPISuperInitialisationGetInitialisedEClassMethodGenerator {
	private static final String getInitialisedEClassMethodName = "getInitialisedEClass";
	private static final String getInitialisedEClassDocumentation = "Returns the EClass, which this initialisation instance initialises.";

	private static final String getInitialisedEClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return this.newElement().eClass()");

	public EOperation generateGetInitialisedEClassMethod() {
		return FluentAPIGenerationUtil.generateEOperationWithBodyAndDocumentation(getInitialisedEClassMethodName,
				EcorePackage.Literals.ECLASS, getInitialisedEClassMethodBody, getInitialisedEClassDocumentation);
	}
}
