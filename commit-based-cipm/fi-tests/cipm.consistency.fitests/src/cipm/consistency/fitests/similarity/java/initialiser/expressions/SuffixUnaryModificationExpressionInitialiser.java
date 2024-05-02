package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.SuffixUnaryModificationExpression;

public class SuffixUnaryModificationExpressionInitialiser implements ISuffixUnaryModificationExpressionInitialiser {
	@Override
	public ISuffixUnaryModificationExpressionInitialiser newInitialiser() {
		return new SuffixUnaryModificationExpressionInitialiser();
	}

	@Override
	public SuffixUnaryModificationExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createSuffixUnaryModificationExpression();
	}
}