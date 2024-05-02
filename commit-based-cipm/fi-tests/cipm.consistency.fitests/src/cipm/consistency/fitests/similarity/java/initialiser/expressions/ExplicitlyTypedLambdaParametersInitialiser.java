package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExplicitlyTypedLambdaParameters;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class ExplicitlyTypedLambdaParametersInitialiser implements IExplicitlyTypedLambdaParametersInitialiser {
	@Override
	public IExplicitlyTypedLambdaParametersInitialiser newInitialiser() {
		return new ExplicitlyTypedLambdaParametersInitialiser();
	}

	@Override
	public ExplicitlyTypedLambdaParameters instantiate() {
		return ExpressionsFactory.eINSTANCE.createExplicitlyTypedLambdaParameters();
	}
}