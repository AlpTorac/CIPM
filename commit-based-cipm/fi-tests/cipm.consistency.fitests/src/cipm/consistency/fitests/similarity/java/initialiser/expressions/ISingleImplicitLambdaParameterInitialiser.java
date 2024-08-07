package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;

public interface ISingleImplicitLambdaParameterInitialiser extends IImplicitlyTypedLambdaParametersInitialiser {
	@Override
	public SingleImplicitLambdaParameter instantiate();
}
