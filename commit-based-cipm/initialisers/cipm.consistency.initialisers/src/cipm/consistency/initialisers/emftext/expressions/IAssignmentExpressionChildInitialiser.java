package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;

import cipm.consistency.initialisers.emftext.annotations.IAnnotationValueInitialiser;

public interface IAssignmentExpressionChildInitialiser extends IAnnotationValueInitialiser, IExpressionInitialiser {
	@Override
	public AssignmentExpressionChild instantiate();
}
