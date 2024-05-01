package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;

public interface IConditionalInitialiser extends ICommentableInitialiser {
	public default void setCondition(Conditional cond, Expression expr) {
		if (expr != null) {
			cond.setCondition(expr);
			assert cond.getCondition().equals(expr);
		}
	}
}
