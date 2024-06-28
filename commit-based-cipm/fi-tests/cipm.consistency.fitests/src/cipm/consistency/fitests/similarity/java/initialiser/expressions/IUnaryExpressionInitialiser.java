package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.operators.UnaryOperator;

public interface IUnaryExpressionInitialiser extends IMultiplicativeExpressionChildInitialiser {
	public default void addOperator(UnaryExpression ue, UnaryOperator op) {
		if (op != null) {
			ue.getOperators().add(op);
			assert ue.getOperators().contains(op);
		}
	}
	
	public default void addOperators(UnaryExpression ue, UnaryOperator[] ops) {
		this.addXs(ue, ops, this::addOperator);
	}
	
	public default void setChild(UnaryExpression ue, UnaryExpressionChild child) {
		if (child != null) {
			ue.setChild(child);
			assert ue.getChild().equals(child);
		}
	}
}