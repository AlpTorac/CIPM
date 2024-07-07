package cipm.consistency.fitests.similarity.java.initialiser.instantiations;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;

import cipm.consistency.fitests.similarity.java.initialiser.ModificationMethod;
import cipm.consistency.fitests.similarity.java.initialiser.commons.ICommentableInitialiser;

public interface IInitializableInitialiser extends ICommentableInitialiser {
    @Override
    public Initializable instantiate();
    @ModificationMethod
	public default boolean setInitialValue(Initializable initializable, Expression initVal) {
		if (initVal != null) {
			initializable.setInitialValue(initVal);
			return initializable.getInitialValue().equals(initVal);
		}
		return true;
	}
}
