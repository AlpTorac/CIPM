package cipm.consistency.fitests.similarity.java.initialiser.members;

import org.emftext.language.java.members.ClassMethod;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.statements.IStatementListContainerInitialiser;

public interface IClassMethodInitialiser extends IMethodInitialiser, IStatementListContainerInitialiser {
    @Override
    public ClassMethod instantiate();
	/**
	 * No statements can be added to {@link ClassMethod} via {@code cm.getStatements().add(statement)}.
	 * <br><br>
	 * @return True, since the {@link ClassMethod} instance (slc here) is not modified.
	 */
	@Override
	default boolean addStatementAssertion(StatementListContainer slc, Statement s) {
		return true;
	}
}
