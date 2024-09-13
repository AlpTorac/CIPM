package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalExpressionChild;

public interface IConditionalExpressionChildInitialiser extends IAssignmentExpressionChildInitialiser {
	@Override
	public ConditionalExpressionChild instantiate();

}
