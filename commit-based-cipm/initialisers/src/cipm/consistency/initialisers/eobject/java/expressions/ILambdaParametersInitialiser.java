package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.LambdaParameters;

import cipm.consistency.initialisers.eobject.java.parameters.IParametrizableInitialiser;

public interface ILambdaParametersInitialiser extends IParametrizableInitialiser {
	@Override
	public LambdaParameters instantiate();

}
