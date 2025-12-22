package cipm.consistency.fluentapi.gen.init;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIToAPIMethodGenerator {
	private static final String toAPIMethodName = "toAPI";

	private static final String toAPIMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("return this.getRootAPI()");

	public EOperation generateToAPIMethod(EClass rootAPIEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(toAPIMethodName, FluentAPIConstants.getGenModelURL(),
				rootAPIEClass, toAPIMethodBodyTemplate);
	}
}
