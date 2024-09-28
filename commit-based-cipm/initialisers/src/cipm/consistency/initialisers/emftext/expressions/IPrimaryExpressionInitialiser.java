package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.PrimaryExpression;

public interface IPrimaryExpressionInitialiser extends IMethodReferenceExpressionChildInitialiser {
	@Override
	public PrimaryExpression instantiate();

}