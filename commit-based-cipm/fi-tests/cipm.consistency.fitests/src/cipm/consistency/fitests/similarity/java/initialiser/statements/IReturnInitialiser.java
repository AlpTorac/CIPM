package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IReturnInitialiser extends IStatementInitialiser {
    @Override
    public Return instantiate();
    @ModificationMethod
	public default boolean setReturnValue(Return ret, Expression expr) {
		if (expr != null) {
			ret.setReturnValue(expr);
			return ret.getReturnValue().equals(expr);
		}
		return true;
	}
}
