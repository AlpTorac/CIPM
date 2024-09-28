package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.ExpressionList;

import cipm.consistency.initialisers.emftext.statements.IForLoopInitializerInitialiser;

public interface IExpressionListInitialiser extends IForLoopInitializerInitialiser {
	@Override
	public ExpressionList instantiate();

	public default boolean addExpression(ExpressionList exprList, Expression expr) {
		if (expr != null) {
			exprList.getExpressions().add(expr);
			return exprList.getExpressions().contains(expr);
		}
		return true;
	}

	public default boolean addExpressions(ExpressionList exprList, Expression[] exprs) {
		return this.doMultipleModifications(exprList, exprs, this::addExpression);
	}
}
