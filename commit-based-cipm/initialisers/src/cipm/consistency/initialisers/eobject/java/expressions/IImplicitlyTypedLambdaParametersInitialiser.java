package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;

public interface IImplicitlyTypedLambdaParametersInitialiser extends ILambdaParametersInitialiser {
	@Override
	public ImplicitlyTypedLambdaParameters instantiate();
}
