package cipm.consistency.initialisers.eobject.java.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;

import cipm.consistency.initialisers.eobject.java.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.initialisers.eobject.java.types.ITypedElementInitialiser;

public interface IClassTypeConstructorReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ClassTypeConstructorReferenceExpression instantiate();
}
