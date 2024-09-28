package cipm.consistency.initialisers.emftext.instantiations;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;

import cipm.consistency.initialisers.emftext.commons.ICommentableInitialiser;

public interface IInitializableInitialiser extends ICommentableInitialiser {
	@Override
	public Initializable instantiate();

	public default boolean setInitialValue(Initializable initializable, Expression initVal) {
		if (initVal != null) {
			initializable.setInitialValue(initVal);
			return initializable.getInitialValue().equals(initVal);
		}
		return true;
	}
}
