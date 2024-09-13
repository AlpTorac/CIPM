package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.expressions.AdditiveExpression;

public class AdditiveExpressionInitialiser extends AbstractInitialiserBase implements IAdditiveExpressionInitialiser {
	@Override
	public IAdditiveExpressionInitialiser newInitialiser() {
		return new AdditiveExpressionInitialiser();
	}

	@Override
	public AdditiveExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAdditiveExpression();
	}
}
