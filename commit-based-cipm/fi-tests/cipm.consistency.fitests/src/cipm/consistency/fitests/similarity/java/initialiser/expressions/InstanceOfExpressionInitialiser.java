package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.InstanceOfExpression;

public class InstanceOfExpressionInitialiser implements IInstanceOfExpressionInitialiser {
	@Override
	public IInstanceOfExpressionInitialiser newInitialiser() {
		return new InstanceOfExpressionInitialiser();
	}

	@Override
	public InstanceOfExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createInstanceOfExpression();
	}
}