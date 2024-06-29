package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;

public interface IConditionalOrExpressionInitialiser extends IConditionalExpressionChildInitialiser {
	public default boolean addChild(ConditionalOrExpression coe, ConditionalOrExpressionChild child) {
		if (child != null) {
			coe.getChildren().add(child);
			return coe.getChildren().contains(child);
		}
		return false;
	}
	
	public default boolean addChildren(ConditionalOrExpression coe, ConditionalOrExpressionChild[] children) {
		return this.addXs(coe, children, this::addChild);
	}
}
