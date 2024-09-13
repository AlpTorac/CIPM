package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpression;

public interface IMethodReferenceExpressionInitialiser extends IUnaryModificationExpressionChildInitialiser {
	@Override
	public MethodReferenceExpression instantiate();

}
