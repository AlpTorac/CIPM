package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;

public interface IConditionalAndExpressionInitialiser extends IConditionalAndExpressionChildInitialiser {
	public default boolean addChild(ConditionalAndExpression cae, ConditionalAndExpressionChild child) {
		if (child != null) {
			cae.getChildren().add(child);
			return cae.getChildren().contains(child);
		}
		return false;
	}
	
	public default boolean addChildren(ConditionalAndExpression cae, ConditionalAndExpressionChild[] children) {
		return this.addXs(cae, children, this::addChild);
	}
}