package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ConditionalOrExpression;
import org.emftext.language.java.expressions.ConditionalOrExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IConditionalOrExpressionInitialiser extends IConditionalExpressionChildInitialiser {
    @Override
    public ConditionalOrExpression instantiate();
    @ModificationMethod
	public default boolean addChild(ConditionalOrExpression coe, ConditionalOrExpressionChild child) {
		if (child != null) {
			coe.getChildren().add(child);
			return coe.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(ConditionalOrExpression coe, ConditionalOrExpressionChild[] children) {
		return this.addXs(coe, children, this::addChild);
	}
}
