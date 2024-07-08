package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementContainer;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementContainerInitialiser extends ICommentableInitialiser {
    @Override
    public StatementContainer instantiate();
    @ModificationMethod
	public default boolean setStatement(StatementContainer sc, Statement s) {
		if (s != null) {
			sc.setStatement(s);
			return sc.getStatement().equals(s);
		}
		return true;
	}
}
