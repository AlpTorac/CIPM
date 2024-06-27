package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

public class ReceiverParameterInitialiser implements IReceiverParameterInitialiser {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}

	@Override
	public IReceiverParameterInitialiser newInitialiser() {
		return new ReceiverParameterInitialiser();
	}
}
