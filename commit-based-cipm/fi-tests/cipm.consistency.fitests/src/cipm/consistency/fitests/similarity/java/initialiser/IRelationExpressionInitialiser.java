package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.operators.RelationOperator;

public interface IRelationExpressionInitialiser extends IInstanceOfExpressionChildInitialiser {
	public default void addRelationOperator(RelationExpression re, RelationOperator op) {
		if (op != null) {
			re.getRelationOperators().add(op);
			assert re.getRelationOperators().contains(op);
		}
	}
}