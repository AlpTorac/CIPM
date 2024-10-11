package cipm.consistency.initialisers.jamopp.expressions;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.initialisers.jamopp.types.ITypedElementInitialiser;

public interface ICastExpressionInitialiser
		extends ITypedElementInitialiser, IUnaryModificationExpressionChildInitialiser {
	@Override
	public CastExpression instantiate();

	public default boolean addAdditionalBound(CastExpression ce, TypeReference additionalBounds) {
		if (additionalBounds != null) {
			ce.getAdditionalBounds().add(additionalBounds);
			return ce.getAdditionalBounds().contains(additionalBounds);
		}
		return true;
	}

	public default boolean addAdditionalBounds(CastExpression ce, TypeReference[] additionalBoundsArr) {
		return this.doMultipleModifications(ce, additionalBoundsArr, this::addAdditionalBound);
	}

	/**
	 * {@code ce.getChild()} has the same return value as
	 * {@code ce.getGeneralChild()}, it merely returns the child attribute as
	 * {@link Expression} rather than {@link MultiplicativeExpressionChild}.
	 */
	public default boolean setChild(CastExpression ce, MultiplicativeExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			return ce.getChild().equals(child);
		}
		return true;
	}
}
