package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ShiftExpression;
import org.emftext.language.java.expressions.ShiftExpressionChild;
import org.emftext.language.java.operators.ShiftOperator;

public interface IShiftExpressionInitialiser extends IRelationExpressionChildInitialiser {
	public default void addShiftOperator(ShiftExpression se, ShiftOperator op) {
		if (op != null) {
			se.getShiftOperators().add(op);
			assert se.getShiftOperators().contains(op);
		}
	}
	
	public default void addShiftOperators(ShiftExpression se, ShiftOperator[] ops) {
		this.addXs(se, ops, this::addShiftOperator);
	}
	
	public default void addChild(ShiftExpression se, ShiftExpressionChild child) {
		if (child != null) {
			se.getChildren().add(child);
			assert se.getChildren().contains(child);
		}
	}
	
	public default void addChildren(ShiftExpression se, ShiftExpressionChild[] children) {
		this.addXs(se, children, this::addChild);
	}
}