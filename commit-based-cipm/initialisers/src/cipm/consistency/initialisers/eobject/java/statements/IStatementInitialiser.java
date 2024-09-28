package cipm.consistency.initialisers.eobject.java.statements;

import org.emftext.language.java.statements.Statement;

import cipm.consistency.initialisers.eobject.java.commons.ICommentableInitialiser;

public interface IStatementInitialiser extends ICommentableInitialiser {
	@Override
	public Statement instantiate();
}