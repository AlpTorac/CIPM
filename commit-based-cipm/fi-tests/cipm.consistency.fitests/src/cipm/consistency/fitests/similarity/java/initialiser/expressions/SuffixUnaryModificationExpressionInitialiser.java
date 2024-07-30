package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.SuffixUnaryModificationExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class SuffixUnaryModificationExpressionInitialiser extends AbstractInitialiserBase
		implements ISuffixUnaryModificationExpressionInitialiser {
	@Override
	public ISuffixUnaryModificationExpressionInitialiser newInitialiser() {
		return new SuffixUnaryModificationExpressionInitialiser();
	}

	@Override
	public SuffixUnaryModificationExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createSuffixUnaryModificationExpression();
	}
}