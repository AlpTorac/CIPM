package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IReceiverParameterInitialiser;

public class ReceiverParameterInitialiser extends ParameterInitialiser implements IReceiverParameterInitialiser {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}
	
	@Override
	public ReceiverParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (ReceiverParameterInitialiser) super.withPInit(pInit);
	}
}
