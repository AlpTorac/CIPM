package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;

import org.emftext.language.java.parameters.OrdinaryParameter;

public class OrdinaryParameterInitialiser extends ParameterInitialiser implements IOrdinaryParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}
	
	@Override
	public OrdinaryParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (OrdinaryParameterInitialiser) super.withPInit(pInit);
	}

	@Override
	public ParameterInitialiser newInitialiser() {
		return new OrdinaryParameterInitialiser();
	}
}
