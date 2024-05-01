package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.NestedExpression;

public interface INestedExpressionInitialiser extends IReferenceInitialiser {
	public default void setExpression(NestedExpression ne, Expression expr) {
		if (expr != null) {
			ne.setExpression(expr);
			assert ne.getExpression().equals(expr);
		}
	}
}
