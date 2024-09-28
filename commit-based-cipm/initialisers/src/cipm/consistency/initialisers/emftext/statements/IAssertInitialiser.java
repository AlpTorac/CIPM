package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;

public interface IAssertInitialiser extends IConditionalInitialiser, IStatementInitialiser {
	@Override
	public Assert instantiate();

	public default boolean setErrorMessage(Assert asrt, Expression errMsg) {
		if (errMsg != null) {
			asrt.setErrorMessage(errMsg);
			return asrt.getErrorMessage().equals(errMsg);
		}
		return true;
	}
}
