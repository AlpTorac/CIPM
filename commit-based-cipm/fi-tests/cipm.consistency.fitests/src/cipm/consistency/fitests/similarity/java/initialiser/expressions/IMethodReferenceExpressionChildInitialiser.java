package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpressionChild;

public interface IMethodReferenceExpressionChildInitialiser extends IUnaryModificationExpressionChildInitialiser {
	@Override
	public MethodReferenceExpressionChild instantiate();

}