package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.OrdinaryParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IOrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;

public class OrdinaryParameterInitialiser extends ParameterInitialiser implements IOrdinaryParameterInitialiser {
	public OrdinaryParameterInitialiser() {
		super();
	}
	
	public OrdinaryParameterInitialiser(IParametrizableInitialiser pInit) {
		super(pInit);
	}

	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}
}
