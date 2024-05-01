package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalExpressionInitialiser;

public class ConditionalExpressionInitialiser implements IConditionalExpressionInitialiser {
	@Override
	public IConditionalExpressionInitialiser newInitialiser() {
		return new ConditionalExpressionInitialiser();
	}

	@Override
	public ConditionalExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createConditionalExpression();
	}
}