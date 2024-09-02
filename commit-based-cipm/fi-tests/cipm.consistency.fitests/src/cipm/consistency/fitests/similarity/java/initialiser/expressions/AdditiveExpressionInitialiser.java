package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.AdditiveExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

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
