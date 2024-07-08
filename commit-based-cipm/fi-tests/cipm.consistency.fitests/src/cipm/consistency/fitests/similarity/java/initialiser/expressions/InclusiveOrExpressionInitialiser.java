package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.InclusiveOrExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class InclusiveOrExpressionInitialiser extends AbstractInitialiserBase implements IInclusiveOrExpressionInitialiser {
	@Override
	public IInclusiveOrExpressionInitialiser newInitialiser() {
		return new InclusiveOrExpressionInitialiser();
	}

	@Override
	public InclusiveOrExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createInclusiveOrExpression();
	}
}