package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.expressions.Expression;
import org.emftext.language.java.imports.ImportingElement;
import org.emftext.language.java.instantiations.Initializable;

import cipm.consistency.fitests.similarity.java.initialiser.ICommentableInitialiser;

public interface IInitializableInitialiser extends ICommentableInitialiser {
	public default void setInitialValue(Initializable initializable, Expression initVal) {
		if (initVal != null) {
			initializable.setInitialValue(initVal);
			assert initializable.getInitialValue().equals(initVal);
		}
	}
}