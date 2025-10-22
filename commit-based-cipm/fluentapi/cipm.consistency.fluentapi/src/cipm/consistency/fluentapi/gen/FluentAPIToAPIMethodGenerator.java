package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;

public class FluentAPIToAPIMethodGenerator {
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String toAPIMethodName = "toAPI";

	private static final String toAPIMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC("return this.getRootAPI()");

	public EOperation generateToAPIMethod(EClass rootAPIEClass) {
		return FluentAPIGenerationUtil.generateEOperationWithBody(toAPIMethodName, genModelURL, rootAPIEClass,
				toAPIMethodBodyTemplate);
	}
}
