package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.parameters.OrdinaryParameter;
import org.emftext.language.java.statements.CatchBlock;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class CatchBlockSimilaritySwitch extends StatementsSimilaritySwitch {

	public CatchBlockSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return CatchBlock.class;
	}

	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		CatchBlock catchBlock1 = (CatchBlock) eo1;
		CatchBlock catchBlock2 = (CatchBlock) eo2;

		OrdinaryParameter catchedException1 = catchBlock1.getParameter();
		OrdinaryParameter catchedException2 = catchBlock2.getParameter();

		Boolean exceptionSimilarity = sc.isSimilar(catchedException1, catchedException2);
		if (exceptionSimilarity == Boolean.FALSE) {
			return exceptionSimilarity;
		}

		return Boolean.TRUE;
	}
}
