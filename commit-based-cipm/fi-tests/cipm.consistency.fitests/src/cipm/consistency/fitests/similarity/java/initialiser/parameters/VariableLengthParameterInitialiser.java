package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class VariableLengthParameterInitialiser extends AbstractInitialiserBase implements IVariableLengthParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate() {
		return ParametersFactory.eINSTANCE.createVariableLengthParameter();
	}

	@Override
	public IVariableLengthParameterInitialiser newInitialiser() {
		return new VariableLengthParameterInitialiser();
	}
}
