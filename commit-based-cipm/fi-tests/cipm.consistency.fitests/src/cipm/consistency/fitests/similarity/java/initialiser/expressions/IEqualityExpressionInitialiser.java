package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.operators.EqualityOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IAndExpressionChildInitialiser;

public interface IEqualityExpressionInitialiser extends IAndExpressionChildInitialiser {
	public default void addEqualityOperator(EqualityExpression eqEx, EqualityOperator op) {
		if (op != null) {
			eqEx.getEqualityOperators().add(op);
			assert eqEx.getEqualityOperators().contains(op);
		}
	}
}