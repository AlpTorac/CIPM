package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.MultiplicativeExpressionChild;

public interface IMultiplicativeExpressionChildInitialiser extends IAdditiveExpressionChildInitialiser {
	@Override
	public MultiplicativeExpressionChild instantiate();

}