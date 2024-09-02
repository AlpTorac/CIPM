package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IArrayConstructorReferenceExpressionInitialiser
		extends IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayConstructorReferenceExpression instantiate();
}
