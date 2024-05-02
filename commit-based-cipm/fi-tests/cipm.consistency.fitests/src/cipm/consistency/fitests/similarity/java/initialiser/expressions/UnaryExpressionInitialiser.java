package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.UnaryExpression;

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