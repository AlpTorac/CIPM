package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.ShiftExpression;

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