package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.AndExpressionChild;

public interface IAndExpressionChildInitialiser extends IExclusiveOrExpressionChildInitialiser {
	@Override
	public AndExpressionChild instantiate();

}