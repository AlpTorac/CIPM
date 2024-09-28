package cipm.consistency.initialisers.emftext.statements;

import org.emftext.language.java.statements.Statement;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IStatementInitialiser extends ICommentableInitialiser {
	@Override
	public Statement instantiate();
}