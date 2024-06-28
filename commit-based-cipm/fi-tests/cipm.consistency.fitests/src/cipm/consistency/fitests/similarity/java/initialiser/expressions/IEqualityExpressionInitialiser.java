package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.operators.EqualityOperator;

public interface IEqualityExpressionInitialiser extends IAndExpressionChildInitialiser {
	public default void addEqualityOperator(EqualityExpression eqEx, EqualityOperator op) {
		if (op != null) {
			eqEx.getEqualityOperators().add(op);
			assert eqEx.getEqualityOperators().contains(op);
		}
	}
	
	public default void addChild(EqualityExpression eqEx, EqualityExpressionChild child) {
		if (child != null) {
			eqEx.getChildren().add(child);
			assert eqEx.getChildren().contains(child);
		}
	}
	
	public default void addChildren(EqualityExpression eqEx, EqualityExpressionChild[] children) {
		this.addXs(eqEx, children, this::addChild);
	}
}