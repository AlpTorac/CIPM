package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.EqualityExpressionChild;

public interface IEqualityExpressionChildInitialiser extends IAndExpressionChildInitialiser {
	@Override
	public EqualityExpressionChild instantiate();

}