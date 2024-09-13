package cipm.consistency.fitests.similarity.java.eobject.initialiser.arrays;

import org.emftext.language.java.arrays.ArraySelector;
import org.emftext.language.java.expressions.Expression;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.annotations.IAnnotableInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.commons.ICommentableInitialiser;

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
