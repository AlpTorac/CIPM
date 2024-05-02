package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IReturnInitialiser extends IStatementInitialiser {
	public default void setReturnValue(Return ret, Expression expr) {
		if (expr != null) {
			ret.setReturnValue(expr);
			assert ret.getReturnValue().equals(expr);
		}
	}
}
