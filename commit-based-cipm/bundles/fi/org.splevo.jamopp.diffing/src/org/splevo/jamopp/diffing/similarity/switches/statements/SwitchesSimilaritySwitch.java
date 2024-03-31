package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.Switch;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class SwitchesSimilaritySwitch extends StatementsSimilaritySwitch {

	public SwitchesSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return Switch.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		Switch switch1 = (Switch) eo1;
		Switch switch2 = (Switch) eo2;

		return sc.isSimilar(switch1.getVariable(), switch2.getVariable());
	}

}
