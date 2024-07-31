package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementListContainerInitialiser extends ICommentableInitialiser {
	@Override
	public StatementListContainer instantiate();

	// TODO: Remove the statement methods here after implementing the missing
	// impltests. They most likely do not modify the instance.

	/**
	 * Attempts to add the given {@link Statement} to the given
	 * {@link StatementListContainer}. <br>
	 * <br>
	 * <b>Note: Not all implementors of {@link StatementListContainer} allow adding
	 * statements via this method.</b>
	 * 
	 * @return True, if the behaviour expected by calling this method was achieved.
	 * @see {@link #addStatementAssertion(StatementListContainer, Statement)}
	 */
	public default boolean addStatement(StatementListContainer slc, Statement st) {
		if (st != null) {
			slc.getStatements().add(st);
			return this.addStatementAssertion(slc, st);
		}
		return true;
	}

	/**
	 * Extracted from {@link #addStatement(StatementListContainer, Statement)}
	 * because there are some implementors of {@link StatementListContainer}, which
	 * are not modified via: <br>
	 * <br>
	 * {@code slc.getStatements().add(statement)} <br>
	 * <br>
	 * This allows overriding the expectations from
	 * {@link #addStatement(StatementListContainer, Statement)} in sub-interfaces
	 * with deviating behaviour.
	 * 
	 * @return True, if the behaviour expected by calling
	 *         {@link #addStatement(StatementListContainer, Statement)} was
	 *         achieved.
	 */
	default boolean addStatementAssertion(StatementListContainer slc, Statement st) {
		return slc.getStatements().contains(st);
	}

	public default boolean addStatements(StatementListContainer slc, Statement[] sts) {
		return this.doMultipleModifications(slc, sts, this::addStatement);
	}
}
