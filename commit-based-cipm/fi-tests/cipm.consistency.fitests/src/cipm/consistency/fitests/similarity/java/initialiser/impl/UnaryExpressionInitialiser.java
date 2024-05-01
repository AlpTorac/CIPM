package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.UnaryExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IUnaryExpressionInitialiser;

public class UnaryExpressionInitialiser implements IUnaryExpressionInitialiser {
	@Override
	public IUnaryExpressionInitialiser newInitialiser() {
		return new UnaryExpressionInitialiser();
	}

	@Override
	public UnaryExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createUnaryExpression();
	}
}