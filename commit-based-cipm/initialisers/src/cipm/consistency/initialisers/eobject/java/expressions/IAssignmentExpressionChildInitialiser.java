package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;

import cipm.consistency.initialisers.eobject.java.annotations.IAnnotationValueInitialiser;

public interface IAssignmentExpressionChildInitialiser extends IAnnotationValueInitialiser, IExpressionInitialiser {
	@Override
	public AssignmentExpressionChild instantiate();
}
