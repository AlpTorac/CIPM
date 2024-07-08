package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.LambdaExpression;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class LambdaExpressionInitialiser extends AbstractInitialiserBase implements ILambdaExpressionInitialiser {

	@Override
	public ILambdaExpressionInitialiser newInitialiser() {
		return new LambdaExpressionInitialiser();
	}

	@Override
	public LambdaExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createLambdaExpression();
	}

}
