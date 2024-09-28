package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IConditionalInitialiser extends ICommentableInitialiser {
	@Override
	public Conditional instantiate();

	public default boolean setCondition(Conditional cond, Expression condExpr) {
		if (condExpr != null) {
			cond.setCondition(condExpr);
			return cond.getCondition().equals(condExpr);
		}
		return true;
	}
}
