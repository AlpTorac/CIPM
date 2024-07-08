package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.MultiplicativeExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class MultiplicativeExpressionInitialiser extends AbstractInitialiserBase implements IMultiplicativeExpressionInitialiser {
	@Override
	public IMultiplicativeExpressionInitialiser newInitialiser() {
		return new MultiplicativeExpressionInitialiser();
	}

	@Override
	public MultiplicativeExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createMultiplicativeExpression();
	}
}