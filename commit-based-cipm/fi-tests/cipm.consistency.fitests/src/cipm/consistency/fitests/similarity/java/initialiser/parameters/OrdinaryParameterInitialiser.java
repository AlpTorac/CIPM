package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.OrdinaryParameter;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
