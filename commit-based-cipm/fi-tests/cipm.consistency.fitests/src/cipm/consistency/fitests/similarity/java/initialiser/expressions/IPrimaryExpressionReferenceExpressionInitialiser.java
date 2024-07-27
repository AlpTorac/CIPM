package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.java.initialiser.generics.ICallTypeArgumentableInitialiser;

public interface IPrimaryExpressionReferenceExpressionInitialiser extends
	ICallTypeArgumentableInitialiser,
	IMethodReferenceExpressionInitialiser {
	@Override
	public PrimaryExpressionReferenceExpression instantiate();
	public default boolean setChild(PrimaryExpressionReferenceExpression pere, MethodReferenceExpressionChild mrec) {
		if (mrec != null) {
			pere.setChild(mrec);
			return pere.getChild().equals(mrec);
		}
		return true;
	}
	public default boolean setMethodReference(PrimaryExpressionReferenceExpression pere, Reference ref) {
		if (ref != null) {
			pere.setMethodReference(ref);
			return pere.getMethodReference().equals(ref);
		}
		return true;
	}
}
