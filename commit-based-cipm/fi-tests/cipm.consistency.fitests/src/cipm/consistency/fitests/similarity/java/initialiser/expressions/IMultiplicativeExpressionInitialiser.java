package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.operators.MultiplicativeOperator;

public interface IMultiplicativeExpressionInitialiser extends IMultiplicativeExpressionChildInitialiser {
	public default void addMultiplicativeOperator(MultiplicativeExpression ae, MultiplicativeOperator op) {
		if (op != null) {
			ae.getMultiplicativeOperators().add(op);
			assert ae.getMultiplicativeOperators().contains(op);
		}
	}
	
	public default void addMultiplicativeOperators(MultiplicativeExpression ae, MultiplicativeOperator[] ops) {
		this.addXs(ae, ops, this::addMultiplicativeOperator);
	}
	
	public default void addChild(MultiplicativeExpression ae, MultiplicativeExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			assert ae.getChildren().contains(child);
		}
	}
	
	public default void addChildren(MultiplicativeExpression ae, MultiplicativeExpressionChild[] children) {
		this.addXs(ae, children, this::addChild);
	}
}