package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Condition;
import org.emftext.language.java.statements.Statement;

import cipm.consistency.fitests.similarity.java.initialiser.IStatementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IConditionalInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IStatementContainerInitialiser;

public interface IConditionInitialiser extends IConditionalInitialiser,
	IStatementInitialiser,
	IStatementContainerInitialiser {
	
	public default void setElseStatement(Condition cond, Statement st) {
		if (st != null) {
			cond.setElseStatement(st);
			assert cond.getElseStatement().equals(st);
		}
	}
}
