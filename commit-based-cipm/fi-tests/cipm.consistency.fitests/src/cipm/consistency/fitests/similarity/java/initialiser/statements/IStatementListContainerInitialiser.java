package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementListContainerInitialiser extends ICommentableInitialiser {
	
	// TODO: Remove the statement methods here after implementing the missing impltests. They most likely do not modify the instance.
	
	public default boolean addStatement(StatementListContainer slc, Statement s) {
		if (s != null) {
			slc.getStatements().add(s);
			return slc.getStatements().contains(s);
		}
		return true;
	}
	
	public default boolean addStatements(StatementListContainer slc, Statement[] ss) {
		return this.addXs(slc, ss, this::addStatement);
	}
}
