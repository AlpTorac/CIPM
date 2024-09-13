package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IArrayConstructorReferenceExpressionInitialiser
		extends IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayConstructorReferenceExpression instantiate();
}
