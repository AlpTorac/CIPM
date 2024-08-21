package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.java.initialiser.AbstractInitialiserBase;

public class AndExpressionInitialiser extends AbstractInitialiserBase implements IAndExpressionInitialiser {
	@Override
	public IAndExpressionInitialiser newInitialiser() {
		return new AndExpressionInitialiser();
	}

	@Override
	public AndExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAndExpression();
	}
}