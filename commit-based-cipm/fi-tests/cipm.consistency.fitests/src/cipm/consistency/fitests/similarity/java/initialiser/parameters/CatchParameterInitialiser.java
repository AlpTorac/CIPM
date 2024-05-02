package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.java.initialiser.EObjectInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IParametrizableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ParameterInitialiser;

import org.emftext.language.java.parameters.CatchParameter;

public class CatchParameterInitialiser extends ParameterInitialiser implements ICatchParameterInitialiser {
	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}
	
	@Override
	public CatchParameterInitialiser withPInit(IParametrizableInitialiser pInit) {
		return (CatchParameterInitialiser) super.withPInit(pInit);
	}

	@Override
	public ParameterInitialiser newInitialiser() {
		return new CatchParameterInitialiser();
	}
}
