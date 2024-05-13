package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MethodReferenceExpressionChild;
import org.emftext.language.java.expressions.PrimaryExpressionReferenceExpression;
import org.emftext.language.java.references.Reference;

import cipm.consistency.fitests.similarity.java.initialiser.IMethodReferenceExpressionInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.ICallTypeArgumentableInitialiser;

public interface IPrimaryExpressionReferenceExpressionInitialiser extends
	ICallTypeArgumentableInitialiser,
	IMethodReferenceExpressionInitialiser {

	public default void setChild(PrimaryExpressionReferenceExpression pere, MethodReferenceExpressionChild mrec) {
		if (mrec != null) {
			pere.setChild(mrec);
			assert pere.getChild().equals(mrec);
		}
	}
	
	public default void setMethodReference(PrimaryExpressionReferenceExpression pere, Reference ref) {
		if (ref != null) {
			pere.setMethodReference(ref);
			assert pere.getMethodReference().equals(ref);
		}
	}
}