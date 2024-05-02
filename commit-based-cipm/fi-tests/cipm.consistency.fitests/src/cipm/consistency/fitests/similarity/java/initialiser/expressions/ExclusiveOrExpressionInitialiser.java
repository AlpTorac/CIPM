package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class ExclusiveOrExpressionInitialiser implements IExclusiveOrExpressionInitialiser {
	@Override
	public IExclusiveOrExpressionInitialiser newInitialiser() {
		return new ExclusiveOrExpressionInitialiser();
	}

	@Override
	public ExclusiveOrExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createExclusiveOrExpression();
	}
}