package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Jump;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class JumpSimilaritySwitch extends StatementsSimilaritySwitch {

	public JumpSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return Jump.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		Jump jump1 = (Jump) eo1;
		Jump jump2 = (Jump) eo2;

		Boolean similarity = sc.isSimilar(jump1.getTarget(), jump2.getTarget());
		if (similarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		return Boolean.TRUE;
	}
}
