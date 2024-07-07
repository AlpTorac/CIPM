package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IInclusiveOrExpressionInitialiser extends IConditionalAndExpressionChildInitialiser {
    @Override
    public InclusiveOrExpression instantiate();
    @ModificationMethod
	public default boolean addChild(InclusiveOrExpression ioe, InclusiveOrExpressionChild child) {
		if (child != null) {
			ioe.getChildren().add(child);
			return ioe.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(InclusiveOrExpression ioe, InclusiveOrExpressionChild[] children) {
		return this.addXs(ioe, children, this::addChild);
	}
}