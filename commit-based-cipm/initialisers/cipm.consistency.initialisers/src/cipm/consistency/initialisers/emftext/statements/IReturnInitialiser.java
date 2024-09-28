package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;

public interface IReturnInitialiser extends IStatementInitialiser {
	@Override
	public Return instantiate();

	public default boolean setReturnValue(Return ret, Expression retVal) {
		if (retVal != null) {
			ret.setReturnValue(retVal);
			return ret.getReturnValue().equals(retVal);
		}
		return true;
	}
}
