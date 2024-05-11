package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;

public class ReceiverParameterInitialiser implements IReceiverParameterInitialiser {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}

	@Override
	public ReceiverParameterInitialiser newInitialiser() {
		return new ReceiverParameterInitialiser();
	}
}
