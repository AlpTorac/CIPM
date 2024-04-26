package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.instantiations.Initializable;

public interface IInitializableInitialiser extends ICommentableInitialiser {
	public default void setInitialValue(Initializable initializable, Expression initVal) {
		if (initVal != null) {
			initializable.setInitialValue(initVal);
			assert initializable.getInitialValue().equals(initVal);
		}
	}
}
