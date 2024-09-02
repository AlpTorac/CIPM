package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ClassTypeConstructorReferenceExpression;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ICallTypeArgumentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface IClassTypeConstructorReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser, ITypedElementInitialiser {
	@Override
	public ClassTypeConstructorReferenceExpression instantiate();
}
