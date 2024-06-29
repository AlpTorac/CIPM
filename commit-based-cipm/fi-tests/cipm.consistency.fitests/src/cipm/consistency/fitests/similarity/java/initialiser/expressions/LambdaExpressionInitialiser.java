package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.LambdaExpression;

public class LambdaExpressionInitialiser implements ILambdaExpressionInitialiser {

	@Override
	public ILambdaExpressionInitialiser newInitialiser() {
		return new LambdaExpressionInitialiser();
	}

	@Override
	public LambdaExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createLambdaExpression();
	}

}
