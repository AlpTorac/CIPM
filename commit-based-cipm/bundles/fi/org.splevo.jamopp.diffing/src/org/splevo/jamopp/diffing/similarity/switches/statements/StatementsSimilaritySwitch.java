package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Statement;
import org.emftext.language.java.statements.StatementListContainer;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;
import org.splevo.jamopp.diffing.similarity.switches.AbstractSimilaritySwitch;
import org.splevo.jamopp.util.JaMoPPElementUtil;

public abstract class StatementsSimilaritySwitch extends AbstractSimilaritySwitch {
	private boolean checkStatementPosition;

	public StatementsSimilaritySwitch(boolean checkStatementPosition) {
		this.checkStatementPosition = checkStatementPosition;
	}

	public boolean isCheckStatementPosition() {
		return this.checkStatementPosition;
	}

	@Override
	public Boolean defaultCase(EObject eo1, EObject eo2) {
		return Boolean.TRUE;
	}

	/**
	 * Decide of two statements differ from each other or not.
	 * 
	 * @param statement1 The first statement to compare
	 * @param statement2 The second statement to compare.
	 * @return True if they differ, null if not.
	 */
	protected boolean differentPredecessor(Statement statement1, Statement statement2, SimilarityChecker sc) {
		Statement pred1 = getPredecessor(statement1);
		Statement pred2 = getPredecessor(statement2);
		Boolean similarity = sc.isSimilar(pred1, pred2, false);
		return similarity == Boolean.FALSE;
	}

	/**
	 * Check if two statements have differing successor statements.
	 * 
	 * @param statement1 The first statement to check.
	 * @param statement2 The second statement to check.
	 * @return True if their successor differ, false if not.
	 */
	protected boolean differentSuccessor(Statement statement1, Statement statement2, SimilarityChecker sc) {
		Statement pred1 = getSuccessor(statement1);
		Statement pred2 = getSuccessor(statement2);
		Boolean similarity = sc.isSimilar(pred1, pred2, false);
		return similarity == Boolean.FALSE;
	}

	/**
	 * Get the predecessor statement of a statement within the parents container
	 * statement list.<br>
	 * If a statement is the first, the only one, or the container is not a
	 * {@link StatementListContainer}, or no predecessor exists, null will be
	 * returned.
	 * 
	 * @param statement The statement to get the predecessor for.
	 * @return The predecessor or null if non exists.
	 */
	protected Statement getPredecessor(Statement statement) {

		int pos = JaMoPPElementUtil.getPositionInContainer(statement);
		if (pos > 0) {
			StatementListContainer container = (StatementListContainer) statement.eContainer();
			return container.getStatements().get(pos - 1);
		}

		return null;
	}

	/**
	 * Get the successor statement of a statement within the parents container
	 * statement list.<br>
	 * If a statement is the last, the only one, or the container is not a
	 * {@link StatementListContainer}, no successor exists, null will be returned.
	 * 
	 * @param statement The statement to get the predecessor for.
	 * @return The predecessor or null if non exists.
	 */
	protected Statement getSuccessor(Statement statement) {

		int pos = JaMoPPElementUtil.getPositionInContainer(statement);
		if (pos != -1) {
			StatementListContainer container = (StatementListContainer) statement.eContainer();
			if (container.getStatements().size() > pos + 1) {
				return container.getStatements().get(pos + 1);
			}
		}

		return null;
	}
}
