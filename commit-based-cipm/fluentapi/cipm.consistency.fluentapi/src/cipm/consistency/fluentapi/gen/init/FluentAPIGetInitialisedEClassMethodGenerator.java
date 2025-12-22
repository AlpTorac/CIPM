package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIGetInitialisedEClassMethodGenerator {
	private static final String getInitialisedEClassMethodName = "getInitialisedEClass";

	private static final String getInitialisedEClassMethodBody = FluentAPIMethodsUtil
			.joinLOC("return this.newElement().eClass()");

	public EOperation generateGetInitialisedEClassMethod() {
		return FluentAPIGenerationUtil.generateEOperationWithBody(getInitialisedEClassMethodName,
				FluentAPIConstants.getGenModelURL(), FluentAPIGenerationUtil.getEClassEClass(),
				getInitialisedEClassMethodBody);
	}
}
