package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;

public interface IExplicitlyTypedLambdaParametersInitialiser extends ILambdaParametersInitialiser {
	@Override
	public ExplicitlyTypedLambdaParameters instantiate();
}
