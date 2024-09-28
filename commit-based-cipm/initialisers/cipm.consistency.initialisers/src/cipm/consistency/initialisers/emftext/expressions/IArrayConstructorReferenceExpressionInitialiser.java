package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;

import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IArrayConstructorReferenceExpressionInitialiser
		extends IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayConstructorReferenceExpression instantiate();
}
