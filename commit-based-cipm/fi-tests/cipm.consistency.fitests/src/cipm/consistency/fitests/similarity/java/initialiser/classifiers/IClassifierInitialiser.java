package cipm.consistency.fitests.similarity.java.initialiser.classifiers;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.imports.Import;

import cipm.consistency.fitests.similarity.java.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.types.ITypeInitialiser;

/**
 * An interface meant for {@link IInitialiser} implementors that are supposed to
 * create {@link Classifier} instances. <br>
 * <br>
 * <b>Note: "add..." methods of {@link Classifier} add the imports to its
 * {@link CompilationUnit}. {@link Classifier} has no attributes itself. The
 * same holds for the "add..." methods of this initialiser.</b>
 * 
 * @author atora
 */

public interface IClassifierInitialiser extends ITypeInitialiser, IReferenceableElementInitialiser {
	@Override
	public Classifier instantiate();

	/**
	 * Adds the given {@link Import} to the {@link CompilationUnit} containing the
	 * given {@link Classifier}.
	 */
	public default boolean addImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			return cls.getContainingCompilationUnit().getImports().stream().anyMatch((i) -> i.equals(imp));
		}
		return true;
	}

	public default boolean addImports(Classifier cls, Import[] imps) {
		return this.doMultipleModifications(cls, imps, this::addImport);
	}

	/**
	 * Adds the given {@link Import} to the {@link CompilationUnit} containing the
	 * given {@link Classifier}.
	 */
	public default boolean addPackageImport(Classifier cls, Import imp) {
		if (imp != null) {
			cls.getContainingCompilationUnit().getImports().add(imp);
			return cls.getContainingCompilationUnit().getImports().stream().anyMatch((i) -> i.equals(imp));
		}
		return true;
	}

	public default boolean addPackageImports(Classifier cls, Import[] imps) {
		return this.doMultipleModifications(cls, imps, this::addPackageImport);
	}
}
