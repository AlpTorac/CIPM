package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.operators.RelationOperator;

public interface IRelationExpressionInitialiser extends IInstanceOfExpressionChildInitialiser {
	public default void addRelationOperator(RelationExpression re, RelationOperator op) {
		if (op != null) {
			re.getRelationOperators().add(op);
			assert re.getRelationOperators().contains(op);
		}
	}
	
	public default void addRelationOperators(RelationExpression re, RelationOperator[] ops) {
		this.addXs(re, ops, this::addRelationOperator);
	}
	
	public default void addChild(RelationExpression cae, RelationExpressionChild child) {
		if (child != null) {
			cae.getChildren().add(child);
			assert cae.getChildren().contains(child);
		}
	}
	
	public default void addChildren(RelationExpression cae, RelationExpressionChild[] children) {
		this.addXs(cae, children, this::addChild);
	}
}