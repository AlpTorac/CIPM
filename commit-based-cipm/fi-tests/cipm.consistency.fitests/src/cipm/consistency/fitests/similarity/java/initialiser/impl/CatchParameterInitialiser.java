package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.CatchParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ICatchParameterInitialiser;

public class CatchParameterInitialiser implements ICatchParameterInitialiser {
	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}
}
