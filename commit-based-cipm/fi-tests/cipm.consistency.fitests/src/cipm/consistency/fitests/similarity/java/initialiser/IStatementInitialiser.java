package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.statements.Statement;

public interface IStatementInitialiser extends ICommentableInitialiser {
	@Override
	public Statement instantiate();
}
