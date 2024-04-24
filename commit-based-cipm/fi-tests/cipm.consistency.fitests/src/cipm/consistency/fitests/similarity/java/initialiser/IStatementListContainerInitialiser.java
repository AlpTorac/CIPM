package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

public interface IStatementListContainerInitialiser extends ICommentableInitialiser {
	@Override
	public StatementListContainer instantiate();
	
	public default void addStatement(StatementListContainer slc, Statement s) {
		if (s != null) {
			slc.getStatements().add(s);
			assert slc.getStatements().contains(s);
		}
	}
}
