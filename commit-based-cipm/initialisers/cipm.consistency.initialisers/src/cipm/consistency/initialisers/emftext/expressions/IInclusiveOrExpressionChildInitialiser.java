package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.InclusiveOrExpressionChild;

public interface IInclusiveOrExpressionChildInitialiser extends IConditionalAndExpressionChildInitialiser {
	@Override
	public InclusiveOrExpressionChild instantiate();

}