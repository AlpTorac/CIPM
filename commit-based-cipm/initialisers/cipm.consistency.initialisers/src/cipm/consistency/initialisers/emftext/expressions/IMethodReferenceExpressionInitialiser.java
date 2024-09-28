package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpression;

public interface IMethodReferenceExpressionInitialiser extends IUnaryModificationExpressionChildInitialiser {
	@Override
	public MethodReferenceExpression instantiate();

}
