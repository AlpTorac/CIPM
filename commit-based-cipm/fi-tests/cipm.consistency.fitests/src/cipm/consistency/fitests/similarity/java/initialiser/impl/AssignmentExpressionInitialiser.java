package cipm.consistency.fitests.similarity.java.initialiser.impl;

import cipm.consistency.fitests.similarity.java.initialiser.IAssignmentExpressionInitialiser;

import org.emftext.language.java.expressions.ExpressionsFactory;
import org.emftext.language.java.expressions.AssignmentExpression;

public class AssignmentExpressionInitialiser implements IAssignmentExpressionInitialiser {
	@Override
	public IAssignmentExpressionInitialiser newInitialiser() {
		return new AssignmentExpressionInitialiser();
	}

	@Override
	public AssignmentExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAssignmentExpression();
	}
}