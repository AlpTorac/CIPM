package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.operators.ShiftOperator;

public interface IShiftExpressionInitialiser extends IRelationExpressionChildInitialiser {
	public default void addShiftOperator(ShiftExpression se, ShiftOperator op) {
		if (op != null) {
			se.getShiftOperators().add(op);
			assert se.getShiftOperators().contains(op);
		}
	}
}