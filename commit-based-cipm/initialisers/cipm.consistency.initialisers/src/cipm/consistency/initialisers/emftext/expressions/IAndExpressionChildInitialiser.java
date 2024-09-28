package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.AndExpressionChild;

public interface IAndExpressionChildInitialiser extends IExclusiveOrExpressionChildInitialiser {
	@Override
	public AndExpressionChild instantiate();

}