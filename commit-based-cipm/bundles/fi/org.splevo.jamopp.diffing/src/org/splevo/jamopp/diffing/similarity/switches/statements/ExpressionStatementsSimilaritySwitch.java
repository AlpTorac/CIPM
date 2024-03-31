package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.ExpressionStatement;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class ExpressionStatementsSimilaritySwitch extends StatementsSimilaritySwitch {

	public ExpressionStatementsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return ExpressionStatement.class;
	}

	/**
	 * Check expression statement similarity.<br>
	 * Similarity is checked by
	 * <ul>
	 * <li>similarity statements expressions</li>
	 * </ul>
	 * 
	 * @param statement1 The expression statement to compare with the compare
	 *                   element.
	 * @return True/False if the expression statements are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		ExpressionStatement statement1 = (ExpressionStatement) eo1;
		ExpressionStatement statement2 = (ExpressionStatement) eo2;

		Expression exp1 = statement1.getExpression();
		Expression exp2 = statement2.getExpression();

		Boolean expSimilarity = sc.isSimilar(exp1, exp2);
		if (expSimilarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		// check predecessor similarity
		if (this.isCheckStatementPosition()) {
			if (differentPredecessor(statement1, statement2, sc) && differentSuccessor(statement1, statement2, sc)) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}
}
