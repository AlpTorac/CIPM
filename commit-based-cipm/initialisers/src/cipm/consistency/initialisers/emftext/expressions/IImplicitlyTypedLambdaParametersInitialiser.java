package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;

public interface IImplicitlyTypedLambdaParametersInitialiser extends ILambdaParametersInitialiser {
	@Override
	public ImplicitlyTypedLambdaParameters instantiate();
}
