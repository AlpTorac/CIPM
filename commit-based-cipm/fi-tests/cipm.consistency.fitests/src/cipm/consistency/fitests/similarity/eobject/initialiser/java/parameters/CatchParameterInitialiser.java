package cipm.consistency.fitests.similarity.eobject.initialiser.java.parameters;

import org.emftext.language.java.parameters.ParametersFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.parameters.CatchParameter;

public class CatchParameterInitialiser extends AbstractInitialiserBase implements ICatchParameterInitialiser {
	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}

	@Override
	public ICatchParameterInitialiser newInitialiser() {
		return new CatchParameterInitialiser();
	}
}
