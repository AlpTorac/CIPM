package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;

public interface IImportInitialiser extends INamespaceAwareElementInitialiser {
	public default void setClassifier(Import imp, ConcreteClassifier cls) {
		if (cls != null) {
			imp.setClassifier(cls);
			assert imp.getClassifier().equals(cls);
			// TODO: See if further assertions are possible (over imported things)
		}
	}
}
