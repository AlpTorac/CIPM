package cipm.consistency.initialisers.jamopp.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Throw;

public interface IThrowInitialiser extends IStatementInitialiser {
	@Override
	public Throw instantiate();

	public default boolean setThrowable(Throw th, Expression throwable) {
		if (throwable != null) {
			th.setThrowable(throwable);
			return th.getThrowable().equals(throwable);
		}
		return true;
	}
}
