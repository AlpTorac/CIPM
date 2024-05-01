package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.InclusiveOrExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IInclusiveOrExpressionInitialiser;

public class InclusiveOrExpressionInitialiser implements IInclusiveOrExpressionInitialiser {
	@Override
	public IInclusiveOrExpressionInitialiser newInitialiser() {
		return new InclusiveOrExpressionInitialiser();
	}

	@Override
	public InclusiveOrExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createInclusiveOrExpression();
	}
}