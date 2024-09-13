package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.PrimaryExpression;

public interface IPrimaryExpressionInitialiser extends IMethodReferenceExpressionChildInitialiser {
	@Override
	public PrimaryExpression instantiate();

}