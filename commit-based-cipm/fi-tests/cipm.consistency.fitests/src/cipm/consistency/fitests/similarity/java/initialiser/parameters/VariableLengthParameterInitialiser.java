package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.VariableLengthParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;

public class VariableLengthParameterInitialiser extends ParameterInitialiser implements IVariableLengthParameterInitialiser {
	@Override
	public VariableLengthParameter instantiate() {
		return ParametersFactory.eINSTANCE.createVariableLengthParameter();
	}
	
	@Override
	public VariableLengthParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (VariableLengthParameterInitialiser) super.withPInit(pInit);
	}

	@Override
	public ParameterInitialiser newInitialiser() {
		return new VariableLengthParameterInitialiser();
	}
}
