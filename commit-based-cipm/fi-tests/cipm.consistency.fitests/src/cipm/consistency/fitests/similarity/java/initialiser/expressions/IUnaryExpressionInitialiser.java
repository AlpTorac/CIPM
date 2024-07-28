package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.UnaryExpression;
import org.emftext.language.java.expressions.UnaryExpressionChild;
import org.emftext.language.java.operators.UnaryOperator;

public interface IUnaryExpressionInitialiser extends IMultiplicativeExpressionChildInitialiser {
    @Override
    public UnaryExpression instantiate();
	public default boolean addOperator(UnaryExpression ue, UnaryOperator op) {
		if (op != null) {
			ue.getOperators().add(op);
			return ue.getOperators().contains(op);
		}
		return true;
	}
	
	public default boolean addOperators(UnaryExpression ue, UnaryOperator[] ops) {
		return this.doMultipleModifications(ue, ops, this::addOperator);
	}
	public default boolean setChild(UnaryExpression ue, UnaryExpressionChild child) {
		if (child != null) {
			ue.setChild(child);
			return ue.getChild().equals(child);
		}
		return true;
	}
}