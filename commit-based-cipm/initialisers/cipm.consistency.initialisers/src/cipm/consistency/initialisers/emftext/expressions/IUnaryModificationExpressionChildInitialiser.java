package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.UnaryModificationExpressionChild;

public interface IUnaryModificationExpressionChildInitialiser extends IUnaryExpressionChildInitialiser {
	@Override
	public UnaryModificationExpressionChild instantiate();

}