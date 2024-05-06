package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionList;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class ExpressionListInitialiser implements IExpressionListInitialiser {
	@Override
	public IExpressionListInitialiser newInitialiser() {
		return new ExpressionListInitialiser();
	}

	@Override
	public ExpressionList instantiate() {
		return ExpressionsFactory.eINSTANCE.createExpressionList();
	}
}