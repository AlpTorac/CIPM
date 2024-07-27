package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IArraySelectorInitialiser extends IAnnotableInitialiser,
	ICommentableInitialiser {
	public default boolean setPosition(ArraySelector as, Expression expr) {
		if (expr != null) {
			as.setPosition(expr);
			return as.getPosition().equals(expr);
		}
		return true;
	}
}
