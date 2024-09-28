package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;

public interface IExclusiveOrExpressionChildInitialiser extends IInclusiveOrExpressionChildInitialiser {
	@Override
	public ExclusiveOrExpressionChild instantiate();

}