package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.SingleImplicitLambdaParameter;

import cipm.consistency.fitests.similarity.java.initialiser.ISingleImplicitLambdaParameterInitialiser;

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