package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.EqualityExpression;
import org.emftext.language.java.expressions.EqualityExpressionChild;
import org.emftext.language.java.operators.EqualityOperator;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;

public interface IEqualityExpressionInitialiser extends IAndExpressionChildInitialiser {
    @Override
    public EqualityExpression instantiate();
    @ModificationMethod
	public default boolean addEqualityOperator(EqualityExpression eqEx, EqualityOperator op) {
		if (op != null) {
			eqEx.getEqualityOperators().add(op);
			return eqEx.getEqualityOperators().contains(op);
		}
		return true;
	}
    @ModificationMethod
	public default boolean addChild(EqualityExpression eqEx, EqualityExpressionChild child) {
		if (child != null) {
			eqEx.getChildren().add(child);
			return eqEx.getChildren().contains(child);
		}
		return true;
	}
	
	public default boolean addChildren(EqualityExpression eqEx, EqualityExpressionChild[] children) {
		return this.addXs(eqEx, children, this::addChild);
	}
}