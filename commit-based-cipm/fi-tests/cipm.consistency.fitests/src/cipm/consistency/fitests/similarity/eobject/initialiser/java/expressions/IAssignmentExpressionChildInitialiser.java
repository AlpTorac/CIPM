package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.annotations.IAnnotationValueInitialiser;

public interface IAssignmentExpressionChildInitialiser extends IAnnotationValueInitialiser, IExpressionInitialiser {
	@Override
	public AssignmentExpressionChild instantiate();
}
