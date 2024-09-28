package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.ConditionalOrExpressionChild;

public interface IConditionalOrExpressionChildInitialiser extends IConditionalExpressionChildInitialiser {
	@Override
	public ConditionalOrExpressionChild instantiate();

}