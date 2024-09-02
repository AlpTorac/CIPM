package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class PrimaryExpressionReferenceExpressionInitialiser extends AbstractInitialiserBase
		implements IPrimaryExpressionReferenceExpressionInitialiser {
	@Override
	public IPrimaryExpressionReferenceExpressionInitialiser newInitialiser() {
		return new PrimaryExpressionReferenceExpressionInitialiser();
	}

	@Override
	public PrimaryExpressionReferenceExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createPrimaryExpressionReferenceExpression();
	}
}