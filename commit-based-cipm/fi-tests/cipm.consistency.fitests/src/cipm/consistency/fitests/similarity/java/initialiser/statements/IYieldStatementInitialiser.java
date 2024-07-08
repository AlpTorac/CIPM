package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.YieldStatement;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IYieldStatementInitialiser extends IStatementInitialiser {
    @Override
    public YieldStatement instantiate();
    @ModificationMethod
	public default boolean setYieldExpression(YieldStatement ys, Expression expr) {
		if (expr != null) {
			ys.setYieldExpression(expr);
			return ys.getYieldExpression().equals(expr);
		}
		return true;
	}
}
