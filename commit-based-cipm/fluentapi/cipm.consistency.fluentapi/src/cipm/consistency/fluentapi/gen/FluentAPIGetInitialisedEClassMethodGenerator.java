package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIGetInitialisedEClassMethodGenerator {
	private static final String getInitialisedEClassMethodName = "getInitialisedEClass";

	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String getInitialisedEClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return this.newElement().eClass()");

	public EOperation generateGetInitialisedEClassMethod() {
		return FluentAPIGenerationUtil.generateEOperationWithBody(getInitialisedEClassMethodName, genModelURL,
				FluentAPIGenerationUtil.getEClassEClass(), getInitialisedEClassMethodBody);
	}
}
