package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Return;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class ReturnStatementsSimilaritySwitch extends StatementsSimilaritySwitch {

	public ReturnStatementsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return Return.class;
	}

	/**
	 * Check return statement similarity.<br>
	 * Similarity is checked by
	 * <ul>
	 * <li>expressions similarity</li>
	 * </ul>
	 * 
	 * @param returnStatement1 The return statement to compare with the compare
	 *                         element.
	 * @return True/False if the return statements are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		Return returnStatement1 = (Return) eo1;
		Return returnStatement2 = (Return) eo2;

		Expression exp1 = returnStatement1.getReturnValue();
		Expression exp2 = returnStatement2.getReturnValue();

		return sc.isSimilar(exp1, exp2);
	}
}
