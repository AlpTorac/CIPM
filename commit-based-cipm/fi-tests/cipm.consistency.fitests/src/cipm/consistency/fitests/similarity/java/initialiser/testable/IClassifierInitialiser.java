package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.Classifier;

import cipm.consistency.fitests.similarity.java.initialiser.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeInitialiser;

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	public default void addImport(Classifier cls, String importString) {
		if (importString != null) {
			/*
			 * TODO: Just add the import to the containing compilation unit, addImport just delegates to it.
			 * Make the imports on your own.
			 */
			cls.addImport(importString);
			assert cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.getClassifier().getQualifiedName().equals(importString));
		}
	}
	
	public default void addPackageImport(Classifier cls, String importString) {
		if (importString != null) {
			cls.addPackageImport(importString);
			assert cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.getNamespacesAsString().equals(importString));
		}
	}
}
