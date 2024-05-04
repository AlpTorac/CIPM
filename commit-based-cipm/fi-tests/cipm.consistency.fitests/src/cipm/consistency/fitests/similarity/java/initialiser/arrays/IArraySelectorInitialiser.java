package cipm.consistency.fitests.similarity.java.initialiser.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.testable.IAnnotableInitialiser;

public interface IArraySelectorInitialiser extends IAnnotableInitialiser,
	ICommentableInitialiser {
	public default void setPosition(ArraySelector as, Expression expr) {
		if (expr != null) {
			as.setPosition(expr);
			assert as.getPosition().equals(expr);
		}
	}
}
