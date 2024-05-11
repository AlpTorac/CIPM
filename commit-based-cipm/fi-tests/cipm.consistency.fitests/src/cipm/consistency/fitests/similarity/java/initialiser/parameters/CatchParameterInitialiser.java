package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IParametrizableInitialiser;

import org.emftext.language.java.parameters.CatchParameter;

public class CatchParameterInitialiser implements ICatchParameterInitialiser {
	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}

	@Override
	public CatchParameterInitialiser newInitialiser() {
		return new CatchParameterInitialiser();
	}
}
