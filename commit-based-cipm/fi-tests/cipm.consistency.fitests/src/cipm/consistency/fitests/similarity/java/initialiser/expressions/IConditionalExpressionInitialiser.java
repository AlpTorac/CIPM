package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IConditionalExpressionInitialiser extends IAssignmentExpressionChildInitialiser {
    @Override
    public ConditionalExpression instantiate();
    @ModificationMethod
	public default boolean setChild(ConditionalExpression ce, ConditionalExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			return ce.getChild().equals(child);
		}
		return true;
	}
    @ModificationMethod
	public default boolean setExpressionChild(ConditionalExpression ce, AssignmentExpressionChild child) {
		if (child != null) {
			ce.setExpressionChild(child);
			return ce.getExpressionElse().equals(child);
		}
		return true;
	}
    @ModificationMethod
	public default boolean setExpressionIf(ConditionalExpression ce, Expression expr) {
		if (expr != null) {
			ce.setExpressionIf(expr);
			return ce.getExpressionIf().equals(expr);
		}
		return true;
	}
    @ModificationMethod
	public default boolean setGeneralExpressionElse(ConditionalExpression ce, Expression expr) {
		if (expr != null) {
			ce.setGeneralExpressionElse(expr);
			return ce.getGeneralExpressionElse().equals(expr);
		}
		return true;
	}
}
