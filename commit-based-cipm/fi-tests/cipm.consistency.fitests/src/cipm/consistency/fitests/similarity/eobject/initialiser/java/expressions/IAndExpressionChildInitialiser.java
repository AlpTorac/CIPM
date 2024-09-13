package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.AndExpressionChild;

public interface IAndExpressionChildInitialiser extends IExclusiveOrExpressionChildInitialiser {
	@Override
	public AndExpressionChild instantiate();

}