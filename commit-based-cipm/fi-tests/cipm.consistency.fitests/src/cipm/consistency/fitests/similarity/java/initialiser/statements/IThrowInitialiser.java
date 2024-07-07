package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Throw;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

import org.emftext.language.java.statements.Throw;

import org.emftext.language.java.statements.Throw;

public interface IThrowInitialiser extends IStatementInitialiser {
    @Override
    public Throw instantiate();
    @ModificationMethod
	public default boolean setThrowable(Throw th, Expression expr) {
		if (expr != null) {
			th.setThrowable(expr);
			return th.getThrowable().equals(expr);
		}
		return true;
	}
}
