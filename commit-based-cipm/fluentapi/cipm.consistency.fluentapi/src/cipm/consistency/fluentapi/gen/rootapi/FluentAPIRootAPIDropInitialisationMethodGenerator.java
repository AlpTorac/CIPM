package cipm.consistency.fluentapi.gen.rootapi;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.methods.FluentAPIMethodsUtil;
import cipm.consistency.fluentapi.gen.methods.FluentEObjectAPIMethods;

public class FluentAPIRootAPIDropInitialisationMethodGenerator {
	// TODO Add documentation

	private static final String dropInitialisationMethodBody = FluentAPIMethodsUtil.joinLOC(
			FluentEObjectAPIMethods.class.getName() + ".dropInitialisation(this, "
					+ FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationParameterName() + ")",
			//
			"return this");

	public EOperation generateDropInitialisationMethod(EClass rootAPIEClass, EClass initSuperType) {
		var param = getInitialisationParam(initSuperType);
		var op = FluentAPIGenerationUtil.generateEOperation(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationMethodName(), rootAPIEClass);
		FluentAPIGenerationUtil.addBody(op, dropInitialisationMethodBody);
		FluentAPIGenerationUtil.addEParameters(op, param);
		return op;
	}

	public EParameter getInitialisationParam(EClass initSuperType) {
		return FluentAPIGenerationUtil.generateSingleValuedEParameter(
				FluentAPIRootAPIConstants.getFluentAPIRootAPIDropInitialisationParameterName(), initSuperType);
	}
}
