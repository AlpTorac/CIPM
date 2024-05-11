package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;

import org.emftext.language.java.parameters.OrdinaryParameter;

public class OrdinaryParameterInitialiser implements IOrdinaryParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}

	@Override
	public OrdinaryParameterInitialiser newInitialiser() {
		return new OrdinaryParameterInitialiser();
	}
}
