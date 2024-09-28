package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ArrayConstructorReferenceExpression;

import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IArrayConstructorReferenceExpressionInitialiser
		extends IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ArrayConstructorReferenceExpression instantiate();
}
