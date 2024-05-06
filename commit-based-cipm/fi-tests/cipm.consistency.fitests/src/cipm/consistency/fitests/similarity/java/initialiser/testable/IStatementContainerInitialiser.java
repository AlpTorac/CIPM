package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IStatementContainerInitialiser extends ICommentableInitialiser {
	public default void setStatement(StatementContainer sc, Statement s) {
		if (s != null) {
			sc.setStatement(s);
			assert sc.getStatement().equals(s);
		}
	}
}
