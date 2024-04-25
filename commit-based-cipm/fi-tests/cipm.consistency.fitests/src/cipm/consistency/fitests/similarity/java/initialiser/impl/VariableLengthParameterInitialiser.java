package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IVariableLengthParameterInitialiser;

public class VariableLengthParameterInitialiser implements IVariableLengthParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate() {
		return ParametersFactory.eINSTANCE.createVariableLengthParameter();
	}
}
