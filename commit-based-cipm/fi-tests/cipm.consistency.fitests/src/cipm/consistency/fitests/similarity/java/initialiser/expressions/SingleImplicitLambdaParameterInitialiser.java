package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;

public class SingleImplicitLambdaParameterInitialiser implements ISingleImplicitLambdaParameterInitialiser {
	@Override
	public ISingleImplicitLambdaParameterInitialiser newInitialiser() {
		return new SingleImplicitLambdaParameterInitialiser();
	}

	@Override
	public SingleImplicitLambdaParameter instantiate() {
		return ExpressionsFactory.eINSTANCE.createSingleImplicitLambdaParameter();
	}
}