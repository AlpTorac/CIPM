package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.ImplicitlyTypedLambdaParameters;

import cipm.consistency.fitests.similarity.java.initialiser.IImplicitlyTypedLambdaParametersInitialiser;

public class ImplicitlyTypedLambdaParametersInitialiser implements IImplicitlyTypedLambdaParametersInitialiser {
	@Override
	public IImplicitlyTypedLambdaParametersInitialiser newInitialiser() {
		return new ImplicitlyTypedLambdaParametersInitialiser();
	}

	@Override
	public ImplicitlyTypedLambdaParameters instantiate() {
		return ExpressionsFactory.eINSTANCE.createImplicitlyTypedLambdaParameters();
	}
}