package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpression;

public interface IMethodReferenceExpressionInitialiser extends IUnaryModificationExpressionChildInitialiser {
	@Override
	public MethodReferenceExpression instantiate();

}
