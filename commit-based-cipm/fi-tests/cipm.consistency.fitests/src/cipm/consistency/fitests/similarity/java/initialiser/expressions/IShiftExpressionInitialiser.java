package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.operators.ShiftOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IRelationExpressionChildInitialiser;

public interface IShiftExpressionInitialiser extends IRelationExpressionChildInitialiser {
	public default void addShiftOperator(ShiftExpression se, ShiftOperator op) {
		if (op != null) {
			se.getShiftOperators().add(op);
			assert se.getShiftOperators().contains(op);
		}
	}
}