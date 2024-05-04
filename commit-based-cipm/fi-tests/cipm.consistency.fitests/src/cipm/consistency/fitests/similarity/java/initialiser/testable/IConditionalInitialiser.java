package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IConditionalInitialiser extends ICommentableInitialiser {
	public default void setCondition(Conditional cond, Expression expr) {
		if (expr != null) {
			cond.setCondition(expr);
			assert cond.getCondition().equals(expr);
		}
	}
}
