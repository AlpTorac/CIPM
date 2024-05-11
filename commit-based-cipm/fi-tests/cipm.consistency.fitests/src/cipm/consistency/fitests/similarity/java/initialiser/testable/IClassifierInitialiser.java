package cipm.consistency.fitests.similarity.java.initialiser.testable;

import java.util.Collection;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeInitialiser;

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	// TODO: Clean up after tests are finished
//	public default void addImport(Classifier cls, String importString) {
//		if (importString != null) {
//			cls.addImport(importString);
//			assert cls.getContainingCompilationUnit().getImports().stream()
//			.anyMatch((i) -> i.getClassifier().getQualifiedName().equals(importString));
//		}
//	}
//	
//	public default void addPackageImport(Classifier cls, String importString) {
//		if (importString != null) {
//			cls.addPackageImport(importString);
//			assert cls.getContainingCompilationUnit().getImports().stream()
//			.anyMatch((i) -> i.getNamespacesAsString().equals(importString));
//		}
//	}
	
	public default void addImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			assert cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.equals(imp));
		}
	}
	
	public default void addImports(Classifier cls, Import[] imps) {
		this.addXs(cls, (o, imp)->this.addImport(o, imp), imps);
	}
	
	public default void addImports(Classifier cls, Collection<? extends Import> imps) {
		this.addXs(cls, (o, imp)->this.addImport(o, imp), imps);
	}
	
	public default void addPackageImport(Classifier cls, Import imp) {
		this.addImport(cls, imp);
	}
	
	public default void addPackageImports(Classifier cls, Import[] imp) {
		this.addImports(cls, imp);
	}
	
	public default void addPackageImports(Classifier cls, Collection<? extends Import> imp) {
		this.addImports(cls, imp);
	}
}
