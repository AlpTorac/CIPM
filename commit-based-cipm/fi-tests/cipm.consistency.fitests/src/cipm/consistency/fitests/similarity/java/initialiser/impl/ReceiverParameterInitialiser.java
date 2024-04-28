package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.ReceiverParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IReceiverParameterInitialiser;

public class ReceiverParameterInitialiser implements IReceiverParameterInitialiser, IInitialiser<ReceiverParameter> {
	@Override
	public ReceiverParameter instantiate() {
		return ParametersFactory.eINSTANCE.createReceiverParameter();
	}
}
