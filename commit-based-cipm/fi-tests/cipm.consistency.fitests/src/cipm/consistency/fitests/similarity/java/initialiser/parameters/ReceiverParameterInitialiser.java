package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ReceiverParameterInitialiser extends AbstractInitialiserBase implements IReceiverParameterInitialiser {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}

	@Override
	public IReceiverParameterInitialiser newInitialiser() {
		return new ReceiverParameterInitialiser();
	}
}
