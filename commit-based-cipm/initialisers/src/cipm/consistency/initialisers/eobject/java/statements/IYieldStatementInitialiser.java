package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.YieldStatement;

public interface IYieldStatementInitialiser extends IStatementInitialiser {
	@Override
	public YieldStatement instantiate();

	public default boolean setYieldExpression(YieldStatement ys, Expression yieldExpr) {
		if (yieldExpr != null) {
			ys.setYieldExpression(yieldExpr);
			return ys.getYieldExpression().equals(yieldExpr);
		}
		return true;
	}
}
