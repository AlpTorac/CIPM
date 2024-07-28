package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeInitialiser;

/**
 * Adder methods of Classifier add the imports to its CompilationUnit.
 * Classifier has no attributes itself.
 * 
 * @author atora
 */

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
    @Override
    public Classifier instantiate();
	public default boolean addImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			return cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.equals(imp));
		}
		return true;
	}
    
	public default boolean addImports(Classifier cls, Import[] imps) {
		return this.doMultipleModifications(cls, imps, this::addImport);
	}
	public default boolean addPackageImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			return cls.getContainingCompilationUnit().getImports().stream()
			.anyMatch((i) -> i.equals(imp));
		}
		return true;
	}
	
	public default boolean addPackageImports(Classifier cls, Import[] imps) {
		return this.doMultipleModifications(cls, imps, this::addPackageImport);
	}
}
