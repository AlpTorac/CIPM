package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypedElementInitialiser;

public interface IClassTypeConstructorReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ClassTypeConstructorReferenceExpression instantiate();
}
