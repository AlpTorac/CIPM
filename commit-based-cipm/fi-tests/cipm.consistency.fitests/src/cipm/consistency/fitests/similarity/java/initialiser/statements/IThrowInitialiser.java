package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Throw;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IThrowInitialiser extends IStatementInitialiser {
	public default void setThrowable(Throw th, Expression expr) {
		if (expr != null) {
			th.setThrowable(expr);
			assert th.getThrowable().equals(expr);
		}
	}
}
