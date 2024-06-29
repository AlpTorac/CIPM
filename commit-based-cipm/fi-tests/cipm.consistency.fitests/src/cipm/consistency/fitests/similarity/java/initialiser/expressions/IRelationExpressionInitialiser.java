package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.RelationExpression;
import org.emftext.language.java.expressions.RelationExpressionChild;
import org.emftext.language.java.operators.RelationOperator;

public interface IRelationExpressionInitialiser extends IInstanceOfExpressionChildInitialiser {
	public default boolean addRelationOperator(RelationExpression re, RelationOperator op) {
		if (op != null) {
			re.getRelationOperators().add(op);
			return re.getRelationOperators().contains(op);
		}
		return true;
	}
	
	public default boolean addRelationOperators(RelationExpression re, RelationOperator[] ops) {
		return this.addXs(re, ops, this::addRelationOperator);
	}
	
	public default boolean addChild(RelationExpression cae, RelationExpressionChild child) {
		if (child != null) {
			cae.getChildren().add(child);
			return cae.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(RelationExpression cae, RelationExpressionChild[] children) {
		return this.addXs(cae, children, this::addChild);
	}
}