package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class ConditionalAndExpressionInitialiser implements IConditionalAndExpressionInitialiser {
	@Override
	public IConditionalAndExpressionInitialiser newInitialiser() {
		return new ConditionalAndExpressionInitialiser();
	}

	@Override
	public ConditionalAndExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createConditionalAndExpression();
	}
}