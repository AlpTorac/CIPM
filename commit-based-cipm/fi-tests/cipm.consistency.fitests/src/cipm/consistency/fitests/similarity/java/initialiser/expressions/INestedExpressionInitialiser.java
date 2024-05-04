package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.NestedExpression;

import cipm.consistency.fitests.similarity.java.initialiser.testable.IReferenceInitialiser;

public interface INestedExpressionInitialiser extends IReferenceInitialiser {
	public default void setExpression(NestedExpression ne, Expression expr) {
		if (expr != null) {
			ne.setExpression(expr);
			assert ne.getExpression().equals(expr);
		}
	}
}
