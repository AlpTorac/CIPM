package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class ImplicitlyTypedLambdaParametersInitialiser extends AbstractInitialiserBase
		implements IImplicitlyTypedLambdaParametersInitialiser {
	@Override
	public IImplicitlyTypedLambdaParametersInitialiser newInitialiser() {
		return new ImplicitlyTypedLambdaParametersInitialiser();
	}

	@Override
	public ImplicitlyTypedLambdaParameters instantiate() {
		return ExpressionsFactory.eINSTANCE.createImplicitlyTypedLambdaParameters();
	}
}