package org.splevo.jamopp.diffing.similarity.switches.statements;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.statements.LocalVariableStatement;
import org.emftext.language.java.variables.Variable;
import org.splevo.jamopp.diffing.similarity.SimilarityChecker;

public class LocalVariableStatementsSimilaritySwitch extends StatementsSimilaritySwitch {

	public LocalVariableStatementsSimilaritySwitch(boolean checkStatementPosition) {
		super(checkStatementPosition);
	}

	@Override
	public Class<?> getComparisonSubjectType() {
		return LocalVariableStatement.class;
	}

	/**
	 * Check the similarity of a variable declaration.
	 * 
	 * The similarity is decided by the declared variables name only. A changed
	 * variable type or value initialization should lead to a changed statement not
	 * a new one.
	 * 
	 * @param varStmt1 The variable to compare with the original / right-side one
	 * @return True/False if they are similar or not.
	 */
	@Override
	public Boolean handle(EObject eo1, EObject eo2, SimilarityChecker sc) {
		LocalVariableStatement varStmt1 = (LocalVariableStatement) eo1;
		LocalVariableStatement varStmt2 = (LocalVariableStatement) eo2;

		Variable var1 = varStmt1.getVariable();
		Variable var2 = varStmt2.getVariable();
		Boolean varSimilarity = sc.isSimilar(var1, var2);
		if (varSimilarity == Boolean.FALSE) {
			return Boolean.FALSE;
		}

		if (this.isCheckStatementPosition()) {
			varSimilarity = sc.isSimilar(varStmt1.eContainer(), varStmt2.eContainer(), false);
			if (!varSimilarity) {
				return Boolean.FALSE;
			}
			if (differentPredecessor(varStmt1, varStmt2, sc) && differentSuccessor(varStmt1, varStmt2, sc)) {
				return Boolean.FALSE;
			}
		}

		return Boolean.TRUE;
	}
}
