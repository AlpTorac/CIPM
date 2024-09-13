package cipm.consistency.fitests.similarity.eobject.initialiser.java.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;

import cipm.consistency.fitests.similarity.eobject.initialiser.java.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.eobject.initialiser.java.types.ITypedElementInitialiser;

public interface IClassTypeConstructorReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ClassTypeConstructorReferenceExpression instantiate();
}
