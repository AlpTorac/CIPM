package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;

public interface IConditionInitialiser extends IConditionalInitialiser,
	IStatementInitialiser,
	IStatementContainerInitialiser {
	
	public default boolean setElseStatement(Condition cond, Statement st) {
		if (st != null) {
			cond.setElseStatement(st);
			return cond.getElseStatement().equals(st);
		}
		return false;
	}
}
