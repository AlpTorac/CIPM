package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.LambdaParameters;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.parameters.IParametrizableInitialiser;

public interface ILambdaParametersInitialiser extends IParametrizableInitialiser {
	@Override
	public LambdaParameters instantiate();

}
