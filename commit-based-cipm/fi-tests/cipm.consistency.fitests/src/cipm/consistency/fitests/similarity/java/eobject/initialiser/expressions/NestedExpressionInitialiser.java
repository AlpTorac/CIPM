package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.NestedExpression;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

public class NestedExpressionInitialiser extends AbstractInitialiserBase implements INestedExpressionInitialiser {
	@Override
	public INestedExpressionInitialiser newInitialiser() {
		return new NestedExpressionInitialiser();
	}

	@Override
	public NestedExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createNestedExpression();
	}
}