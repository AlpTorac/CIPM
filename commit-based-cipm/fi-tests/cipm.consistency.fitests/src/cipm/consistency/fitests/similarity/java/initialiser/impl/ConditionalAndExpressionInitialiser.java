package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalAndExpressionInitialiser;

public class ConditionalAndExpressionInitialiser implements IConditionalAndExpressionInitialiser {
	@Override
	public IConditionalAndExpressionInitialiser newInitialiser() {
		return new ConditionalAndExpressionInitialiser();
	}

	@Override
	public ConditionalAndExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createConditionalAndExpression();
	}
}