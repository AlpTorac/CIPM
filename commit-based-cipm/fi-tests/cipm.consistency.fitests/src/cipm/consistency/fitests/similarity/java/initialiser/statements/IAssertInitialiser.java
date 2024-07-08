package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IAssertInitialiser extends IConditionalInitialiser,
	IStatementInitialiser {
	@Override
	public Assert instantiate();
	@ModificationMethod
	public default boolean setErrorMessage(Assert asrt, Expression expr) {
		if (expr != null) {
			asrt.setErrorMessage(expr);
			return asrt.getErrorMessage().equals(expr);
		}
		return true;
	}
}
