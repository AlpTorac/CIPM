package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.ConditionalExpressionChild;

public interface IConditionalExpressionChildInitialiser extends IAssignmentExpressionChildInitialiser {
	@Override
	public ConditionalExpressionChild instantiate();

}
