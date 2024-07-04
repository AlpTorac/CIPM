package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.UnaryModificationExpression;
import org.emftext.language.java.expressions.UnaryModificationExpressionChild;
import org.emftext.language.java.operators.UnaryModificationOperator;

public interface IUnaryModificationExpressionInitialiser extends IUnaryExpressionChildInitialiser {
    @Override
    public UnaryModificationExpression instantiate();
	public default boolean setChild(UnaryModificationExpression ume, UnaryModificationExpressionChild child) {
		if (child != null) {
			ume.setChild(child);
			return ume.getChild().equals(child);
		}
		return true;
	}
	
	public default boolean setOperator(UnaryModificationExpression ume, UnaryModificationOperator op) {
		if (op != null) {
			ume.setOperator(op);
			return ume.getOperator().equals(op);
		}
		return true;
	}
}