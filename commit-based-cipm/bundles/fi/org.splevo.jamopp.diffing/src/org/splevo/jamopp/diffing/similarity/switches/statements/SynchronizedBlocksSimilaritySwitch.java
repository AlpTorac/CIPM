package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.SynchronizedBlock;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class SynchronizedBlocksSimilaritySwitch extends StatementsSimilaritySwitch {

	public SynchronizedBlocksSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return SynchronizedBlock.class;
	}

	/**
	 * Check synchronized statement similarity.<br>
	 * Similarity is checked by
	 * <ul>
	 * <li>expression similarity</li>
	 * </ul>
	 * 
	 * @param statement1 The synchronized statement to compare with the compare
	 *                   element.
	 * @return True/False if the synchronized statements are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {

		SynchronizedBlock statement1 = (SynchronizedBlock) eo1;
		SynchronizedBlock statement2 = (SynchronizedBlock) eo2;

		Expression exp1 = statement1.getLockProvider();
		Expression exp2 = statement2.getLockProvider();
		Boolean similarity = sc.isSimilar(exp1, exp2);
		if (similarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		if (this.isCheckStatementPosition()) {
			if (differentPredecessor(statement1, statement2, sc) && differentSuccessor(statement1, statement2, sc)) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

}
