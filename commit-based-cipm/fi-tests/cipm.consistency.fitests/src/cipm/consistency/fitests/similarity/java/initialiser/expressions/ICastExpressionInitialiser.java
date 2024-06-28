package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface ICastExpressionInitialiser extends ITypedElementInitialiser,
	IUnaryModificationExpressionChildInitialiser {
	
	public default void addAdditionalBound(CastExpression ce, TypeReference tref) {
		if (tref != null) {
			ce.getAdditionalBounds().add(tref);
			assert ce.getAdditionalBounds().contains(tref);
		}
	}
	
	public default void addAdditionalBounds(CastExpression ce, TypeReference[] trefs) {
		this.addXs(ce, trefs, this::addAdditionalBound);
	}
	
	public default void setChild(CastExpression ce, MultiplicativeExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			assert ce.getChild().equals(child);
		}
	}
	
	public default void setGeneralChild(CastExpression ce, Expression expr) {
		if (expr != null) {
			ce.setGeneralChild(expr);
			assert ce.getGeneralChild().equals(expr);
		}
	}
}
