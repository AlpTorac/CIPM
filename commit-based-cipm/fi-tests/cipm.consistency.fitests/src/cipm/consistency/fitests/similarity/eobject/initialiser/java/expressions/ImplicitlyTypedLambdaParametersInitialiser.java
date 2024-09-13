package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

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