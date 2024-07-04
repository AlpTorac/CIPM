package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.ExclusiveOrExpression;
import org.emftext.language.java.expressions.ExclusiveOrExpressionChild;

public interface IExclusiveOrExpressionInitialiser extends IInclusiveOrExpressionChildInitialiser {
    @Override
    public ExclusiveOrExpression instantiate();
	public default boolean addChild(ExclusiveOrExpression eoe, ExclusiveOrExpressionChild child) {
		if (child != null) {
			eoe.getChildren().add(child);
			return eoe.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(ExclusiveOrExpression eoe, ExclusiveOrExpressionChild[] children) {
		return this.addXs(eoe, children, this::addChild);
	}
}