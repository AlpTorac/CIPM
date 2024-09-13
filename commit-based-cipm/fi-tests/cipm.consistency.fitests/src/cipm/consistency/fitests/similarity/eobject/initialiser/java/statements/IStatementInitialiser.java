package cipm.consistency.fitests.similarity.eobject.initialiser.java.statements;

import org.emftext.language.java.statements.Statement;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.commons.ICommentableInitialiser;

public interface IStatementInitialiser extends ICommentableInitialiser {
	@Override
	public Statement instantiate();
}