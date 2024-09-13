package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotationValueInitialiser;

public interface IAssignmentExpressionChildInitialiser extends IAnnotationValueInitialiser, IExpressionInitialiser {
	@Override
	public AssignmentExpressionChild instantiate();
}
