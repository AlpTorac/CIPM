package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.AdditiveExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IAdditiveExpressionInitialiser;

public class AdditiveExpressionInitialiser implements IAdditiveExpressionInitialiser {
	@Override
	public IAdditiveExpressionInitialiser newInitialiser() {
		return new AdditiveExpressionInitialiser();
	}

	@Override
	public AdditiveExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAdditiveExpression();
	}
}
