package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.operators.UnaryModificationOperator;

public interface IUnaryModificationExpressionInitialiser extends IUnaryExpressionChildInitialiser {
	public default void setChild(UnaryModificationExpression ume, UnaryModificationExpressionChild child) {
		if (child != null) {
			ume.setChild(child);
			assert ume.getChild().equals(child);
		}
	}
	
	public default void setOperator(UnaryModificationExpression ume, UnaryModificationOperator op) {
		if (op != null) {
			ume.setOperator(op);
			assert ume.getOperator().equals(op);
		}
	}
}