package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.OrdinaryParameter;

import cipm.consistency.fitests.similarity.java.initialiser.IOrdinaryParameterInitialiser;

public class OrdinaryParameterInitialiser implements IOrdinaryParameterInitialiser, IInitialiser<OrdinaryParameter> {
	@Override
	public OrdinaryParameter instantiate() {
		return ParametersFactory.eINSTANCE.createOrdinaryParameter();
	}
}
