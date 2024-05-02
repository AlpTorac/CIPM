package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Assert;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;

public interface IAssertInitialiser extends IConditionalInitialiser,
	IStatementInitialiser {
	
	public default void setErrorMessage(Assert asrt, Expression expr) {
		if (expr != null) {
			asrt.setErrorMessage(expr);
			assert asrt.getErrorMessage().equals(expr);
		}
	}
}
