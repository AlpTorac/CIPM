package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalAndExpressionChild;

public interface IConditionalAndExpressionChildInitialiser extends IConditionalOrExpressionChildInitialiser {
    @Override
    public ConditionalAndExpressionChild instantiate();
	
}