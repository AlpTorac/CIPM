package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.MultiplicativeExpression;

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