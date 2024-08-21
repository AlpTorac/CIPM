package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ExplicitlyTypedLambdaParametersInitialiser extends AbstractInitialiserBase
		implements IExplicitlyTypedLambdaParametersInitialiser {
	@Override
	public IExplicitlyTypedLambdaParametersInitialiser newInitialiser() {
		return new ExplicitlyTypedLambdaParametersInitialiser();
	}

	@Override
	public ExplicitlyTypedLambdaParameters instantiate() {
		return ExpressionsFactory.eINSTANCE.createExplicitlyTypedLambdaParameters();
	}
}