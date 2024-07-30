package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.NestedExpression;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceInitialiser;

public interface INestedExpressionInitialiser extends IReferenceInitialiser {
	@Override
	public NestedExpression instantiate();

	public default boolean setExpression(NestedExpression ne, Expression expr) {
		if (expr != null) {
			ne.setExpression(expr);
			return ne.getExpression().equals(expr);
		}
		return true;
	}
}
