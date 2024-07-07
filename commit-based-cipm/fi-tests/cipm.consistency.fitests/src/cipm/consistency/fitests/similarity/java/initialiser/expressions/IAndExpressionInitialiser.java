package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.AndExpression;
import org.emftext.language.java.expressions.AndExpressionChild;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IAndExpressionInitialiser extends IExclusiveOrExpressionChildInitialiser {
    @Override
    public AndExpression instantiate();
    @ModificationMethod
	public default boolean addChild(AndExpression ae, AndExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			return ae.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(AndExpression ae, AndExpressionChild[] children) {
		return this.addXs(ae, children, this::addChild);
	}
}