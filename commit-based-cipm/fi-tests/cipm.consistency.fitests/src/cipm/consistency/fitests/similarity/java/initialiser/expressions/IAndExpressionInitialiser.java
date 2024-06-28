package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;

public interface IAndExpressionInitialiser extends IExclusiveOrExpressionChildInitialiser {
	public default void addChild(AndExpression ae, AndExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			assert ae.getChildren().contains(child);
		}
	}
	
	public default void addChildren(AndExpression ae, AndExpressionChild[] children) {
		this.addXs(ae, children, this::addChild);
	}
}