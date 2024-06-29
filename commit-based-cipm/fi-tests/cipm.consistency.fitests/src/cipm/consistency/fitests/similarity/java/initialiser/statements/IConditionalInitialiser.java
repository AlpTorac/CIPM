package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IConditionalInitialiser extends ICommentableInitialiser {
	public default boolean setCondition(Conditional cond, Expression expr) {
		if (expr != null) {
			cond.setCondition(expr);
			return cond.getCondition().equals(expr);
		}
		return false;
	}
}
