package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.MultiplicativeExpressionChild;

public interface IMultiplicativeExpressionChildInitialiser extends IAdditiveExpressionChildInitialiser {
	@Override
	public MultiplicativeExpressionChild instantiate();

}