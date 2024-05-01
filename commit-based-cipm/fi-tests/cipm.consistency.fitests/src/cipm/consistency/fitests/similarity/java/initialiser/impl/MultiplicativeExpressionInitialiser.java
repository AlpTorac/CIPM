package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.MultiplicativeExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IMultiplicativeExpressionInitialiser;

public class MultiplicativeExpressionInitialiser implements IMultiplicativeExpressionInitialiser {
	@Override
	public IMultiplicativeExpressionInitialiser newInitialiser() {
		return new MultiplicativeExpressionInitialiser();
	}

	@Override
	public MultiplicativeExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createMultiplicativeExpression();
	}
}