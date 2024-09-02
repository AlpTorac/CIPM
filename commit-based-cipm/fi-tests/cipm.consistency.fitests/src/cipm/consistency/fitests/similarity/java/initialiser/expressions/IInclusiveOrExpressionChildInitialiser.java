package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InclusiveOrExpressionChild;

public interface IInclusiveOrExpressionChildInitialiser extends IConditionalAndExpressionChildInitialiser {
	@Override
	public InclusiveOrExpressionChild instantiate();

}