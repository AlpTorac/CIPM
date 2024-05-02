package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.InclusiveOrExpression;

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