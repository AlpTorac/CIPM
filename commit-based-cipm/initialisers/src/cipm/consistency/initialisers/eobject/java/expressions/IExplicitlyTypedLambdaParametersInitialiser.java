package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;

public interface IExplicitlyTypedLambdaParametersInitialiser extends ILambdaParametersInitialiser {
	@Override
	public ExplicitlyTypedLambdaParameters instantiate();
}
