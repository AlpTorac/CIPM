package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.ExpressionsFactory;

import cipm.consistency.fitests.similarity.initialiser.AbstractInitialiserBase;

import org.emftext.language.java.expressions.AssignmentExpression;

public class AssignmentExpressionInitialiser extends AbstractInitialiserBase
		implements IAssignmentExpressionInitialiser {
	@Override
	public IAssignmentExpressionInitialiser newInitialiser() {
		return new AssignmentExpressionInitialiser();
	}

	@Override
	public AssignmentExpression instantiate() {
		return ExpressionsFactory.eINSTANCE.createAssignmentExpression();
	}
}