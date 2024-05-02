package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.operators.RelationOperator;

import cipm.consistency.fitests.similarity.java.initialiser.IInstanceOfExpressionChildInitialiser;

public interface IRelationExpressionInitialiser extends IInstanceOfExpressionChildInitialiser {
	public default void addRelationOperator(RelationExpression re, RelationOperator op) {
		if (op != null) {
			re.getRelationOperators().add(op);
			assert re.getRelationOperators().contains(op);
		}
	}
}