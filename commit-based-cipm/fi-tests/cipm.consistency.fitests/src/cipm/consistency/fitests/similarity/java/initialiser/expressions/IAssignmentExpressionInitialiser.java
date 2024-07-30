package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AssignmentExpression;
import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.operators.AssignmentOperator;

public interface IAssignmentExpressionInitialiser extends IExpressionInitialiser {
	@Override
	public AssignmentExpression instantiate();

	public default boolean setAssignmentOperator(AssignmentExpression ae, AssignmentOperator op) {
		if (op != null) {
			ae.setAssignmentOperator(op);
			return ae.getAssignmentOperator().equals(op);
		}
		return true;
	}

	public default boolean setChild(AssignmentExpression ae, AssignmentExpressionChild child) {
		if (child != null) {
			ae.setChild(child);
			return ae.getChild().equals(child);
		}
		return true;
	}

	public default boolean setValue(AssignmentExpression ae, Expression expr) {
		if (expr != null) {
			ae.setValue(expr);
			return ae.getValue().equals(expr);
		}
		return true;
	}
}
