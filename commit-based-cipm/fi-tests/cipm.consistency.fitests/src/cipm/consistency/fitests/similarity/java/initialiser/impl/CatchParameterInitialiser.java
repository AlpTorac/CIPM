package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.CatchParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ICatchParameterInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;

public class CatchParameterInitialiser extends ParameterInitialiser implements ICatchParameterInitialiser {
	public CatchParameterInitialiser() {
		super();
	}
	
	public CatchParameterInitialiser(IParametrizableInitialiser pInit) {
		super(pInit);
	}

	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}
}
