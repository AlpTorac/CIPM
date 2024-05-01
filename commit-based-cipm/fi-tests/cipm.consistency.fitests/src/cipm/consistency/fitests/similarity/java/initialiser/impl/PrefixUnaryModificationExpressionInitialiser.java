package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.PrefixUnaryModificationExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IPrefixUnaryModificationExpressionInitialiser;

public class PrefixUnaryModificationExpressionInitialiser implements IPrefixUnaryModificationExpressionInitialiser {
	@Override
	public IPrefixUnaryModificationExpressionInitialiser newInitialiser() {
		return new PrefixUnaryModificationExpressionInitialiser();
	}

	@Override
	public PrefixUnaryModificationExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createPrefixUnaryModificationExpression();
	}
}