package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Throw;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class ThrowsSimilaritySwitch extends StatementsSimilaritySwitch {

	public ThrowsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return Throw.class;
	}

	/**
	 * Check throw statement similarity.<br>
	 * 
	 * Only one throw statement can exist at the same code location. As a result the
	 * container similarity checked implicitly is enough for this.
	 * 
	 * @param throwStatement1 The throw statement to compare with the compare
	 *                        element.
	 * @return True/False if the throw statements are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		return Boolean.TRUE;
	}

}
