package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IVariableLengthParameterInitialiser;

public class VariableLengthParameterInitialiser extends ParameterInitialiser implements IVariableLengthParameterInitialiser {
	public VariableLengthParameterInitialiser() {
		super();
	}
	
	public VariableLengthParameterInitialiser(IParametrizableInitialiser pInit) {
		super(pInit);
	}

	@Override
	public VariableLengthParameter instantiate() {
		return ParametersFactory.eINSTANCE.createVariableLengthParameter();
	}
}
