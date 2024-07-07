package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.MultiplicativeExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.operators.MultiplicativeOperator;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IMultiplicativeExpressionInitialiser extends IAdditiveExpressionChildInitialiser {
    @Override
    public MultiplicativeExpression instantiate();
    @ModificationMethod
	public default boolean addMultiplicativeOperator(MultiplicativeExpression ae, MultiplicativeOperator op) {
		if (op != null) {
			ae.getMultiplicativeOperators().add(op);
			return ae.getMultiplicativeOperators().contains(op);
		}
		return true;
	}
	
	public default boolean addMultiplicativeOperators(MultiplicativeExpression ae, MultiplicativeOperator[] ops) {
		return this.addXs(ae, ops, this::addMultiplicativeOperator);
	}
	@ModificationMethod
	public default boolean addChild(MultiplicativeExpression ae, MultiplicativeExpressionChild child) {
		if (child != null) {
			ae.getChildren().add(child);
			return ae.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(MultiplicativeExpression ae, MultiplicativeExpressionChild[] children) {
		return this.addXs(ae, children, this::addChild);
	}
}