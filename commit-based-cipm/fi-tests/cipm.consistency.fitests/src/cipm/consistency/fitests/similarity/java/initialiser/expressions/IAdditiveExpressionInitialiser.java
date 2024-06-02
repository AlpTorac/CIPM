package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.expressions.AdditiveExpressionChild;
import org.emftext.language.java.operators.AdditiveOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IShiftExpressionChildInitialiser;

public interface IAdditiveExpressionInitialiser extends IShiftExpressionChildInitialiser {
	public default void addAdditiveOperator(AdditiveExpression ae, AdditiveOperator op) {
		if (op != null) {
			ae.getAdditiveOperators().add(op);
			assert ae.getAdditiveOperators().contains(op);
		}
	}
	
	public default void addAdditiveOperators(AdditiveExpression ae, AdditiveOperator[] ops) {
		this.addXs(ae, ops, this::addAdditiveOperator);
	}
	
	public default void addChild(AdditiveExpression ae, AdditiveExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			assert ae.getChildren().contains(child);
		}
	}
	
	public default void addChildren(AdditiveExpression ae, AdditiveExpressionChild[] children) {
		this.addXs(ae, children, this::addChild);
	}
}