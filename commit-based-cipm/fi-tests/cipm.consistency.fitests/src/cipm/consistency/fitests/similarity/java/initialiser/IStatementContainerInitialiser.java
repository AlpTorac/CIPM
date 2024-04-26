package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;

public interface IStatementContainerInitialiser extends ICommentableInitialiser {
	public default void setStatement(StatementContainer sc, Statement s) {
		if (s != null) {
			sc.setStatement(s);
			assert sc.getStatement().equals(s);
		}
	}
}
