package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.YieldStatement;

public interface IYieldStatementInitialiser extends IStatementInitialiser {
    @Override
    public YieldStatement instantiate();
	public default boolean setYieldExpression(YieldStatement ys, Expression expr) {
		if (expr != null) {
			ys.setYieldExpression(expr);
			return ys.getYieldExpression().equals(expr);
		}
		return true;
	}
}
