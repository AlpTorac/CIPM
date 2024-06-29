package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;

public interface IInclusiveOrExpressionInitialiser extends IInclusiveOrExpressionChildInitialiser {
	public default boolean addChild(InclusiveOrExpression ioe, InclusiveOrExpressionChild child) {
		if (child != null) {
			ioe.getChildren().add(child);
			return ioe.getChildren().contains(child);
		}
		return false;
	}
	
	public default boolean addChildren(InclusiveOrExpression ioe, InclusiveOrExpressionChild[] children) {
		return this.addXs(ioe, children, this::addChild);
	}
}