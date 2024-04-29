package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.parameters.CatchParameter;

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	public default void addImport(Classifier cls, String importString) {
		if (importString != null) {
			cls.addImport(importString);
		}
	}
	
	public default void addPackageImport(Classifier cls, String importString) {
		if (importString != null) {
			cls.addPackageImport(importString);
		}
	}
}
