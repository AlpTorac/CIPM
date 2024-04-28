package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.OrdinaryParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IOrdinaryParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;

public class OrdinaryParameterInitialiser extends ParameterInitialiser implements IOrdinaryParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}
	
	@Override
	public OrdinaryParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (OrdinaryParameterInitialiser) super.withPInit(pInit);
	}
}
