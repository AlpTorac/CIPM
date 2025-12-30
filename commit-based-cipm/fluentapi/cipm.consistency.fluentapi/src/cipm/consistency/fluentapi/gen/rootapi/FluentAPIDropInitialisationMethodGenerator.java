package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIDropInitialisationMethodGenerator {
	// TODO Add documentation
	
	private static final String genModelURL = "http://www.eclipse.org/emf/2002/GenModel";

	private static final String dropInitialisationMethodNameTemplate = "dropInitialisation";

	private static final String dropInitialisationParamName = "initToDrop";

	private static final String dropInitialisationMethodBodyTemplate = FluentAPIMethodsUtil.joinLOC(
			// %s: Initialisation to drop
			FluentEObjectAPIMethods.class.getName() + ".dropInitialisation(this, %s)",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(EClass rootAPIEClass, EClass initSuperType) {
		var param = getInitialisationParam(initSuperType);
		return FluentAPIGenerationUtil.generateEOperationWithBody(dropInitialisationMethodNameTemplate, rootAPIEClass,
				String.format(dropInitialisationMethodBodyTemplate, param.getName()), param);
	}

	public EParameter getInitialisationParam(EClass initSuperType) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(dropInitialisationParamName, initSuperType);
	}
}
