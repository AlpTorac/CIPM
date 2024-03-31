package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.statements.Conditional;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class ConditionalsSimilaritySwitch extends StatementsSimilaritySwitch {

	public ConditionalsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return Conditional.class;
	}

	/**
	 * Check if two conditional statements are similar.
	 * 
	 * Similarity is checked by:
	 * <ul>
	 * <li>similarity of the expressions</li>
	 * </ul>
	 * 
	 * The then and else statements are not checked as part of the condition
	 * statement check because this is only about the container if statement
	 * similarity. The contained statements are checked in a separate step of the
	 * compare process if the enclosing condition statement matches.
	 * 
	 * @param conditional1 The statement to compare with the compare element.
	 * @return True/False whether they are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		Conditional conditional1 = (Conditional) eo1;
		Conditional conditional2 = (Conditional) eo2;

		Expression expression1 = conditional1.getCondition();
		Expression expression2 = conditional2.getCondition();
		Boolean expressionSimilarity = sc.isSimilar(expression1, expression2);
		if (expressionSimilarity == Boolean.FALSE) {
			return expressionSimilarity;
		}

		return Boolean.TRUE;
	}

}
