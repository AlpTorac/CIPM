package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IExpressionStatementInitialiser extends IStatementInitialiser {
	public default void setExpression(ExpressionStatement es, Expression expr) {
		if (expr != null) {
			es.setExpression(expr);
			assert es.getExpression().equals(expr);
		}
	}
}
