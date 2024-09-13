package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.parameters.OrdinaryParameter;

public class OrdinaryParameterInitialiser extends AbstractInitialiserBase implements IOrdinaryParameterInitialiser {
	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}

	@Override
	public IOrdinaryParameterInitialiser newInitialiser() {
		return new OrdinaryParameterInitialiser();
	}
}
