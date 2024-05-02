package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.operators.MultiplicativeOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IAdditiveExpressionChildInitialiser;

public interface IMultiplicativeExpressionInitialiser extends IAdditiveExpressionChildInitialiser {
	public default void addMultiplicativeOperator(MultiplicativeExpression me, MultiplicativeOperator op) {
		if (op != null) {
			me.getMultiplicativeOperators().add(op);
			assert me.getMultiplicativeOperators().contains(op);
		}
	}
}