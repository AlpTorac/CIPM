package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.AssignmentExpressionChild;
import org.emftext.language.java.expressions.ConditionalExpression;
import org.emftext.language.java.expressions.ConditionalExpressionChild;
import org.emftext.language.java.expressions.Expression;

public interface IConditionalExpressionInitialiser extends IAssignmentExpressionChildInitialiser {
	@Override
	public ConditionalExpression instantiate();

	public default boolean setChild(ConditionalExpression ce, ConditionalExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			return ce.getChild().equals(child);
		}
		return true;
	}

	public default boolean setExpressionChild(ConditionalExpression ce, AssignmentExpressionChild exprChild) {
		if (exprChild != null) {
			ce.setExpressionChild(exprChild);
			return ce.getExpressionElse().equals(exprChild);
		}
		return true;
	}

	public default boolean setExpressionIf(ConditionalExpression ce, Expression exprIf) {
		if (exprIf != null) {
			ce.setExpressionIf(exprIf);
			return ce.getExpressionIf().equals(exprIf);
		}
		return true;
	}

	public default boolean setGeneralExpressionElse(ConditionalExpression ce, Expression generalExprElse) {
		if (generalExprElse != null) {
			ce.setGeneralExpressionElse(generalExprElse);
			return ce.getGeneralExpressionElse().equals(generalExprElse);
		}
		return true;
	}
}
