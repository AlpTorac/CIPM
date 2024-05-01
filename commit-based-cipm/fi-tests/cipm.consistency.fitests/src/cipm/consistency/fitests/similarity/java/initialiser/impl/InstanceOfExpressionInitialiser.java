package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.InstanceOfExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IInstanceOfExpressionInitialiser;

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