package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;

public interface IAndExpressionInitialiser extends IExclusiveOrExpressionChildInitialiser {
	public default boolean addChild(AndExpression ae, AndExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			return ae.getChildren().contains(child);
		}
		return false;
	}
	
	public default boolean addChildren(AndExpression ae, AndExpressionChild[] children) {
		return this.addXs(ae, children, this::addChild);
	}
}