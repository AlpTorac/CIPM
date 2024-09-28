package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.LambdaParameters;

import cipm.consistency.initialisers.emftext.parameters.IParametrizableInitialiser;

public interface ILambdaParametersInitialiser extends IParametrizableInitialiser {
	@Override
	public LambdaParameters instantiate();

}
