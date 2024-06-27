package cipm.consistency.fitests.similarity.java.initialiser.testable;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.ITypeInitialiser;

/**
 * Adder methods of Classifier add the imports to its CompilationUnit.
 * Classifier has no attributes itself.
 * 
 * @author atora
 */
public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	
	public default void addImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			assert cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.equals(imp));
		}
	}
	
	public default void addImports(Classifier cls, Import[] imps) {
		this.addXs(cls, imps, this::addImport);
	}
	
	public default void addPackageImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			assert cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.equals(imp));
		}
	}
	
	public default void addPackageImports(Classifier cls, Import[] imps) {
		this.addXs(cls, imps, this::addPackageImport);
	}
}
