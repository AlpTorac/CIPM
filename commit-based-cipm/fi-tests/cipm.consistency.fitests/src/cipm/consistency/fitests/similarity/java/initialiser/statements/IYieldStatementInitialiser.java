package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.YieldStatement;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IYieldStatementInitialiser extends IStatementInitialiser {
	public default void setYieldExpression(YieldStatement ys, Expression expr) {
		if (expr != null) {
			ys.setYieldExpression(expr);
			assert ys.getYieldExpression().equals(expr);
		}
	}
}
