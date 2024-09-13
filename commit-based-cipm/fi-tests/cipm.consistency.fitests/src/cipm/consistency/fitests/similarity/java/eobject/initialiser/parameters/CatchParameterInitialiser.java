package cipm.consistency.fitests.similarity.java.eobject.initialiser.parameters;

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
