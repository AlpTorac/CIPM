package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.JumpLabel;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

import com.google.common.base.Strings;

public class JumpLabelsSimilaritySwitch extends StatementsSimilaritySwitch {

	public JumpLabelsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return JumpLabel.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		JumpLabel label1 = (JumpLabel) eo1;
		JumpLabel label2 = (JumpLabel) eo2;

		String name1 = Strings.nullToEmpty(label1.getName());
		String name2 = Strings.nullToEmpty(label2.getName());

		return (name1.equals(name2));
	}

}
