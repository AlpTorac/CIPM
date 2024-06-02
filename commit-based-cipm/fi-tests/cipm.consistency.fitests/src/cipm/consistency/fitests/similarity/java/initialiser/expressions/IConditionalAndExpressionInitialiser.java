package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalAndExpression;
import org.emftext.language.java.expressions.ConditionalAndExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.IConditionalAndExpressionChildInitialiser;

public interface IConditionalAndExpressionInitialiser extends IConditionalAndExpressionChildInitialiser {
	public default void addChild(ConditionalAndExpression cae, ConditionalAndExpressionChild child) {
		if (child != null) {
			cae.getChildren().add(child);
			assert cae.getChildren().contains(child);
		}
	}
	
	public default void addChildren(ConditionalAndExpression cae, ConditionalAndExpressionChild[] children) {
		this.addXs(cae, children, this::addChild);
	}
}