package cipm.consistency.fitests.similarity.java.initialiser.expressions;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypedElementInitialiser;

public interface ICastExpressionInitialiser extends ITypedElementInitialiser,
	IUnaryModificationExpressionChildInitialiser {
	@Override
	public CastExpression instantiate();
	@ModificationMethod
	public default boolean addAdditionalBound(CastExpression ce, TypeReference tref) {
		if (tref != null) {
			ce.getAdditionalBounds().add(tref);
			return ce.getAdditionalBounds().contains(tref);
		}
		return true;
	}
	
	public default boolean addAdditionalBounds(CastExpression ce, TypeReference[] trefs) {
		return this.addXs(ce, trefs, this::addAdditionalBound);
	}
	@ModificationMethod
	public default boolean setChild(CastExpression ce, MultiplicativeExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			return ce.getChild().equals(child);
		}
		return true;
	}
	@ModificationMethod
	public default boolean setGeneralChild(CastExpression ce, Expression expr) {
		if (expr != null) {
			ce.setGeneralChild(expr);
			return ce.getGeneralChild().equals(expr);
		}
		return true;
	}
}
