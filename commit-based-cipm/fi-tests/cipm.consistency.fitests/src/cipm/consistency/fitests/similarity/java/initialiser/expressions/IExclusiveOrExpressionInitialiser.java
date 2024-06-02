package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.IInclusiveOrExpressionChildInitialiser;

public interface IExclusiveOrExpressionInitialiser extends IInclusiveOrExpressionChildInitialiser {
	public default void addChild(ExclusiveOrExpression eoe, ExclusiveOrExpressionChild child) {
		if (child != null) {
			eoe.getChildren().add(child);
			assert eoe.getChildren().contains(child);
		}
	}
	
	public default void addChildren(ExclusiveOrExpression eoe, ExclusiveOrExpressionChild[] children) {
		this.addXs(eoe, children, this::addChild);
	}
}