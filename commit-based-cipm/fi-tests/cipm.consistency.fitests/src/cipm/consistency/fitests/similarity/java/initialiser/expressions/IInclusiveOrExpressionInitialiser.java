package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.InclusiveOrExpression;
import org.emftext.language.java.expressions.InclusiveOrExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.IInclusiveOrExpressionChildInitialiser;

public interface IInclusiveOrExpressionInitialiser extends IInclusiveOrExpressionChildInitialiser {
	public default void addChild(InclusiveOrExpression ioe, InclusiveOrExpressionChild child) {
		if (child != null) {
			ioe.getChildren().add(child);
			assert ioe.getChildren().contains(child);
		}
	}
	
	public default void addChildren(InclusiveOrExpression ioe, InclusiveOrExpressionChild[] children) {
		this.addXs(ioe, children, this::addChild);
	}
}