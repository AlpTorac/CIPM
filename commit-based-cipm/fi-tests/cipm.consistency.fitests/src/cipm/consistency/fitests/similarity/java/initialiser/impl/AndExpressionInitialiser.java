package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.IAndExpressionInitialiser;

public class AndExpressionInitialiser implements IAndExpressionInitialiser {
	@Override
	public IAndExpressionInitialiser newInitialiser() {
		return new AndExpressionInitialiser();
	}

	@Override
	public AndExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAndExpression();
	}
}