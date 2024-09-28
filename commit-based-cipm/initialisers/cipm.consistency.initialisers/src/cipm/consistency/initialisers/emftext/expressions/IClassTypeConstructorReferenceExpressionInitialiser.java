package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;

import cipm.consistency.initialisers.emftext.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.initialisers.emftext.types.ITypedElementInitialiser;

public interface IClassTypeConstructorReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ClassTypeConstructorReferenceExpression instantiate();
}
