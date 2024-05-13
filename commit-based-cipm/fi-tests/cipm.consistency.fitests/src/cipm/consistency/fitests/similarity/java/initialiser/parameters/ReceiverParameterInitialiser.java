package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;

public class ReceiverParameterInitialiser extends ParameterInitialiser implements IReceiverParameterInitialiser {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}
	
	@Override
	public ReceiverParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (ReceiverParameterInitialiser) super.withPInit(pInit);
	}

	@Override
	public ParameterInitialiser newInitialiser() {
		return new ReceiverParameterInitialiser();
	}
}