package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AdditiveExpression;
import org.emftext.language.java.operators.AdditiveOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IShiftExpressionChildInitialiser;

public interface IAdditiveExpressionInitialiser extends IShiftExpressionChildInitialiser {
	public default void addAdditiveOperator(AdditiveExpression ae, AdditiveOperator op) {
		if (op != null) {
			ae.getAdditiveOperators().add(op);
			assert ae.getAdditiveOperators().contains(op);
		}
	}
}