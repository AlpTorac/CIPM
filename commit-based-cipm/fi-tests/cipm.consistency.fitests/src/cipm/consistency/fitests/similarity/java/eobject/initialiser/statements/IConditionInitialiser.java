package cipm.consistency.fitests.similarity.java.eobject.initialiser.statements;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;

public interface IConditionInitialiser
		extends IConditionalInitialiser, IStatementInitialiser, IStatementContainerInitialiser {

	@Override
	public Condition instantiate();

	public default boolean setElseStatement(Condition cond, Statement elseSt) {
		if (elseSt != null) {
			cond.setElseStatement(elseSt);
			return cond.getElseStatement().equals(elseSt);
		}
		return true;
	}
}
