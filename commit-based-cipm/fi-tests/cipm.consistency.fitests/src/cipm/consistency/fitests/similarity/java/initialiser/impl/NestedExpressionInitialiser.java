package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.NestedExpression;

import cipm.consistency.fitests.similarity.java.initialiser.INestedExpressionInitialiser;

public class NestedExpressionInitialiser implements INestedExpressionInitialiser {
	@Override
	public INestedExpressionInitialiser newInitialiser() {
		return new NestedExpressionInitialiser();
	}

	@Override
	public NestedExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createNestedExpression();
	}
}