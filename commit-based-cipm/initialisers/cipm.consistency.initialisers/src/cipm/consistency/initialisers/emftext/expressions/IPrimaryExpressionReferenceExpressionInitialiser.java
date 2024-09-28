package cipm.consistency.initialisers.emftext.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;

import cipm.consistency.initialisers.emftext.generics.ICallTypeArgumentableInitialiser;

public interface IPrimaryExpressionReferenceExpressionInitialiser
		extends ICallTypeArgumentableInitialiser, IMethodReferenceExpressionInitialiser {
	@Override
	public PrimaryExpressionReferenceExpression instantiate();

	public default boolean setChild(PrimaryExpressionReferenceExpression pere, MethodReferenceExpressionChild mrec) {
		if (mrec != null) {
			pere.setChild(mrec);
			return pere.getChild().equals(mrec);
		}
		return true;
	}

	public default boolean setMethodReference(PrimaryExpressionReferenceExpression pere, Reference metRef) {
		if (metRef != null) {
			pere.setMethodReference(metRef);
			return pere.getMethodReference().equals(metRef);
		}
		return true;
	}
}
