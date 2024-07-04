package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;

public interface IExclusiveOrExpressionChildInitialiser extends IInclusiveOrExpressionChildInitialiser {
    @Override
    public ExclusiveOrExpressionChild instantiate();
	
}