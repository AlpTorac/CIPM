package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementListContainerInitialiser extends ICommentableInitialiser {
	public default void addStatement(StatementListContainer slc, Statement s) {
		if (s != null) {
			slc.getStatements().add(s);
			assert slc.getStatements().contains(s);
		}
	}
	
	public default void addStatements(StatementListContainer slc, Statement[] ss) {
		this.addXs(slc, ss, this::addStatement);
	}
}
