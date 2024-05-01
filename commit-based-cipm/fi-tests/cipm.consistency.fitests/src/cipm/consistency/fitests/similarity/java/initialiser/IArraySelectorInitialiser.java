package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

public interface IArraySelectorInitialiser extends IAnnotableInitialiser {
	public default void setPosition(ArraySelector as, Expression expr) {
		if (expr != null) {
			as.setPosition(expr);
			assert as.getPosition().equals(expr);
		}
	}
}
