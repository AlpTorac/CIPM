package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.VariableLengthParameter;

public class VariableLengthParameterInitialiser implements IVariableLengthParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate() {
		return ParametersFactory.eINSTANCE.createVariableLengthParameter();
	}

	@Override
	public IVariableLengthParameterInitialiser newInitialiser() {
		return new VariableLengthParameterInitialiser();
	}
}
