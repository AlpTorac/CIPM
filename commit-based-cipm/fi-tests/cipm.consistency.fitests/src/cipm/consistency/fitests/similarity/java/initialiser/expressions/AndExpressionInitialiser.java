package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

public class AndExpressionInitialiser implements IAndExpressionInitialiser {
	@Override
	public IAndExpressionInitialiser newInitialiser() {
		return new AndExpressionInitialiser();
	}

	@Override
	public AndExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAndExpression();
	}
}