package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.operators.AssignmentOperator;

public interface IAssignmentExpressionInitialiser extends IExpressionInitialiser {
	public default void setAssignmentOperator(AssignmentExpression ae, AssignmentOperator op) {
		if (op != null) {
			ae.setAssignmentOperator(op);
			assert ae.getAssignmentOperator().equals(op);
		}
	}
	
	public default void setChild(AssignmentExpression ae, AssignmentExpressionChild child) {
		if (child != null) {
			ae.setChild(child);
			assert ae.getChild().equals(child);
		}
	}
	
	public default void setValue(AssignmentExpression ae, Expression expr) {
		if (expr != null) {
			ae.setValue(expr);
			assert ae.getValue().equals(expr);
		}
	}
}
