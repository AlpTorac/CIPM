package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementContainerInitialiser extends ICommentableInitialiser {
	@Override
	public StatementContainer instantiate();

	public default boolean setStatement(StatementContainer sc, Statement st) {
		if (st != null) {
			sc.setStatement(st);
			return sc.getStatement().equals(st);
		}
		return true;
	}
}
