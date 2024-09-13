package cipm.consistency.fitests.similarity.java.eobject.initialiser.expressions;

import org.emftext.language.java.expressions.CastExpression;
import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.expressions.MultiplicativeExpressionChild;
import org.emftext.language.java.types.TypeReference;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypedElementInitialiser;

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

	public default boolean setChild(CastExpression ce, MultiplicativeExpressionChild child) {
		if (child != null) {
			ce.setChild(child);
			return ce.getChild().equals(child);
		}
		return true;
	}

	public default boolean setGeneralChild(CastExpression ce, Expression generalChild) {
		if (generalChild != null) {
			ce.setGeneralChild(generalChild);
			return ce.getGeneralChild().equals(generalChild);
		}
		return true;
	}
}
