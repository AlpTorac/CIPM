package cipm.consistency.fitests.similarity.java.initialiser;

import org.emftext.language.java.classifiers.Classifier;

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	@Override
	public Classifier instantiate();
	
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
