package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;

public interface IExpressionListInitialiser extends IForLoopInitializerInitialiser {
	public default void addExpression(ExpressionList exprList, Expression expr) {
		if (expr != null) {
			exprList.getExpressions().add(expr);
			assert exprList.getExpressions().contains(expr);
		}
	}
}
