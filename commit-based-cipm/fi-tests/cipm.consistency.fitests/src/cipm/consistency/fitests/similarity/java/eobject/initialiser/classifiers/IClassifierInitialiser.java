package cipm.consistency.fitests.similarity.java.eobject.initialiser.classifiers;

import org.emftext.language.java.classifiers.Classifier;
import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.eobject.initialiser.references.IReferenceableElementInitialiser;
import cipm.consistency.fitests.similarity.java.eobject.initialiser.types.ITypeInitialiser;

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
	 * 
	 * @see {@link IClassifierInitialiser}
	 */
	public default boolean addImport(Classifier cls, Import imp) {
		if (!this.canAddImports(cls)) {
			return false;
		}
		if (imp != null) {
			var cu = cls.getContainingCompilationUnit();
			cu.getImports().add(imp);
			return cu.getImports().stream().anyMatch((i) -> i.equals(imp));
		}
		return true;
	}

	public default boolean addImports(Classifier cls, Import[] imps) {
		return this.doMultipleModifications(cls, imps, this::addImport);
	}

	/**
	 * @return Whether {@link Import}s can be added via
	 *         {@link #addImport(Classifier, Import)}
	 */
	public default boolean canAddImports(Classifier cls) {
		return cls.getContainingCompilationUnit() != null;
	}

	/**
	 * @return Whether {@link PackageImport}s can be added via
	 *         {@link #addPackageImport(Classifier, PackageImport)}
	 */
	public default boolean canAddPackageImports(Classifier cls) {
		return cls.getContainingCompilationUnit() != null;
	}

	/**
	 * Adds the given {@link PackageImport} to the {@link CompilationUnit}
	 * containing the given {@link Classifier}.
	 * 
	 * @see {@link IClassifierInitialiser}
	 */
	public default boolean addPackageImport(Classifier cls, PackageImport imp) {
		if (!this.canAddPackageImports(cls)) {
			return false;
		}
		if (imp != null) {
			var cu = cls.getContainingCompilationUnit();
			cu.getImports().add(imp);
			return cu.getImports().stream().anyMatch((i) -> i.equals(imp));
		}
		return true;
	}

	public default boolean addPackageImports(Classifier cls, PackageImport[] imps) {
		return this.doMultipleModifications(cls, imps, this::addPackageImport);
	}
}
