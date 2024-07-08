package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.CatchParameter;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
