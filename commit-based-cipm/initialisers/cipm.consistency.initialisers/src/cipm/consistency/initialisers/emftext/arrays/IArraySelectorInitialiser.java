package cipm.consistency.initialisers.emftext.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.initialisers.emftext.annotations.IAnnotableInitialiser;
import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IArraySelectorInitialiser extends IAnnotableInitialiser, ICommentableInitialiser {
	@Override
	public ArraySelector instantiate();

	public default boolean setPosition(ArraySelector as, Expression pos) {
		if (pos != null) {
			as.setPosition(pos);
			return as.getPosition().equals(pos);
		}
		return true;
	}
}
