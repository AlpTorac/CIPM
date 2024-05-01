package cipm.consistency.fitests.similarity.java.initialiser.impl;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.ShiftExpression;

import cipm.consistency.fitests.similarity.java.initialiser.IShiftExpressionInitialiser;

public class ShiftExpressionInitialiser implements IShiftExpressionInitialiser {
	@Override
	public IShiftExpressionInitialiser newInitialiser() {
		return new ShiftExpressionInitialiser();
	}

	@Override
	public ShiftExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createShiftExpression();
	}
}