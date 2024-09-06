package cipm.consistency.fitests.similarity.java.initialiser.statements;

import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;

import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IStatementListContainerInitialiser extends ICommentableInitialiser {
	@Override
	public StatementListContainer instantiate();

	/**
	 * Attempts to add the given {@link Statement} to the given
	 * {@link StatementListContainer}. <br>
	 * <br>
	 * <b>Note: Not all implementors of {@link StatementListContainer} allow adding
	 * statements via this method.</b>
	 * 
	 * @return True, if the given {@link Statement} was added successfully.
	 * @see {@link #addStatementAssertion(StatementListContainer, Statement)}
	 */
	public default boolean addStatement(StatementListContainer slc, Statement st) {
		if (!this.canContainStatements(slc)) {
			return false;
		}
		if (st != null) {
			slc.getStatements().add(st);
			return slc.getStatements().contains(st);
		}
		return true;
	}

	/**
	 * Extracted from {@link #addStatement(StatementListContainer, Statement)}
	 * because there are some implementors of {@link StatementListContainer}, which
	 * throw {@link NullPointerException} if {@code slc.getStatements()} is called
	 * without proper initialisation. Extracting this method allows such
	 * implementors to indicate, whether {@link Statement}s can be added to them
	 * without issues.
	 * 
	 * @return Whether {@link Statement}s can be added to the given
	 *         {@link StatementListContainer} via
	 *         {@link #addStatement(StatementListContainer, Statement)}.
	 */
	public boolean canContainStatements(StatementListContainer slc);

	public default boolean addStatements(StatementListContainer slc, Statement[] sts) {
		return this.doMultipleModifications(slc, sts, this::addStatement);
	}
}
