package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.UnaryExpressionChild;

public interface IUnaryExpressionChildInitialiser extends IMultiplicativeExpressionChildInitialiser {
	@Override
	public UnaryExpressionChild instantiate();

}