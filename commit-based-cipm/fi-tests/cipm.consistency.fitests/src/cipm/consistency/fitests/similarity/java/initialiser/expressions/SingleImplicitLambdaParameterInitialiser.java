package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class SingleImplicitLambdaParameterInitialiser extends AbstractInitialiserBase implements ISingleImplicitLambdaParameterInitialiser {
	@Override
	public ISingleImplicitLambdaParameterInitialiser newInitialiser() {
		return new SingleImplicitLambdaParameterInitialiser();
	}

	@Override
	public SingleImplicitLambdaParameter instantiate() {
		return ExpressionsFactory.eINSTANCE.createSingleImplicitLambdaParameter();
	}
}