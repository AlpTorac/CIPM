package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class ConditionalOrExpressionInitialiser extends AbstractInitialiserBase
		implements IConditionalOrExpressionInitialiser {
	@Override
	public IConditionalOrExpressionInitialiser newInitialiser() {
		return new ConditionalOrExpressionInitialiser();
	}

	@Override
	public ConditionalOrExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createConditionalOrExpression();
	}
}