package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;

public interface ISingleImplicitLambdaParameterInitialiser extends IImplicitlyTypedLambdaParametersInitialiser {
	@Override
	public SingleImplicitLambdaParameter instantiate();
}