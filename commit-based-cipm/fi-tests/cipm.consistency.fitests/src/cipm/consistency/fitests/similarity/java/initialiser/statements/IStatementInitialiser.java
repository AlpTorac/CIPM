package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementInitialiser extends ICommentableInitialiser {
    @Override
    public Statement instantiate();
}