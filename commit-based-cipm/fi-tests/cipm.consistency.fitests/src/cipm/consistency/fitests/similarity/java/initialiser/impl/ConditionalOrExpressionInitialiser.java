package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalOrExpressionInitialiser;

public class ConditionalOrExpressionInitialiser implements IConditionalOrExpressionInitialiser {
	@Override
	public IConditionalOrExpressionInitialiser newInitialiser() {
		return new ConditionalOrExpressionInitialiser();
	}

	@Override
	public ConditionalOrExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
	}
}