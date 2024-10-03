package cipm.consistency.initialisers.jamopp.imports;

import org.emftext.language.java.classifiers.ConcreteClassifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.initialisers.jamopp.commons.INamespaceAwareElementInitialiser;

public interface IImportInitialiser extends INamespaceAwareElementInitialiser {
	@Override
	public Import instantiate();

	public default boolean setClassifier(Import imp, ConcreteClassifier cls) {
		if (cls != null) {
			imp.setClassifier(cls);
			return imp.getClassifier().equals(cls);
		}
		return true;
	}
}
