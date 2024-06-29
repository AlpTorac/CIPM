package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;

public interface IExpressionStatementInitialiser extends IStatementInitialiser {
	public default boolean setExpression(ExpressionStatement es, Expression expr) {
		if (expr != null) {
			es.setExpression(expr);
			return es.getExpression().equals(expr);
		}
		return false;
	}
}
