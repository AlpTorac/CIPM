package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;

public interface IAssertInitialiser extends IConditionalInitialiser,
	IStatementInitialiser {
	
	public default boolean setErrorMessage(Assert asrt, Expression expr) {
		if (expr != null) {
			asrt.setErrorMessage(expr);
			return asrt.getErrorMessage().equals(expr);
		}
		return true;
	}
}
