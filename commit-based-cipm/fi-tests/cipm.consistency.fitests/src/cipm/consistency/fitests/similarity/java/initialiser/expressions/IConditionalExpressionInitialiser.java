package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;

public interface IConditionalExpressionInitialiser extends IAssignmentExpressionChildInitialiser {
	public default void setChild(ConditionalExpression ce, ConditionalExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			assert ce.getChild().equals(child);
		}
	}
	
	public default void setExpressionChild(ConditionalExpression ce, AssignmentExpressionChild child) {
		if (child != null) {
			ce.setExpressionChild(child);
			assert ce.getExpressionElse().equals(child);
		}
	}
	
	public default void setExpressionIf(ConditionalExpression ce, Expression expr) {
		if (expr != null) {
			ce.setExpressionIf(expr);
			assert ce.getExpressionIf().equals(expr);
		}
	}
	
	public default void setGeneralExpressionElse(ConditionalExpression ce, Expression expr) {
		if (expr != null) {
			ce.setGeneralExpressionElse(expr);
			assert ce.getGeneralExpressionElse().equals(expr);
		}
	}
}
