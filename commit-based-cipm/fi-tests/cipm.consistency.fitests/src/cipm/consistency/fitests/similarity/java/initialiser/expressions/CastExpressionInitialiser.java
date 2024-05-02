package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class CastExpressionInitialiser implements ICastExpressionInitialiser {
	@Override
	public ICastExpressionInitialiser newInitialiser() {
		return new CastExpressionInitialiser();
	}

	@Override
	public CastExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createCastExpression();
	}
}