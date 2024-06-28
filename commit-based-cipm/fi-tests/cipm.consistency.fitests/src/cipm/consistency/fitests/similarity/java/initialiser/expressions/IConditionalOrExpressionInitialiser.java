package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;

public interface IConditionalOrExpressionInitialiser extends IConditionalExpressionChildInitialiser {
	public default void addChild(ConditionalOrExpression coe, ConditionalOrExpressionChild child) {
		if (child != null) {
			coe.getChildren().add(child);
			assert coe.getChildren().contains(child);
		}
	}
	
	public default void addChildren(ConditionalOrExpression coe, ConditionalOrExpressionChild[] children) {
		this.addXs(coe, children, this::addChild);
	}
}
