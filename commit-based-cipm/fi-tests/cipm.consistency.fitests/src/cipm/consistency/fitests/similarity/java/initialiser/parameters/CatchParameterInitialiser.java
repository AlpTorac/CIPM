package cipm.consistency.fitests.similarity.java.initialiser.parameters;

import org.emftext.language.java.parameters.ParametersFactory;
import org.emftext.language.java.parameters.CatchParameter;

public class CatchParameterInitialiser implements ICatchParameterInitialiser {
	@Override
	public CatchParameter instantiate() {
		return ParametersFactory.eINSTANCE.createCatchParameter();
	}
	
	@Override
	public ICatchParameterInitialiser newInitialiser() {
		return new CatchParameterInitialiser();
	}
}
