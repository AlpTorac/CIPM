package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.imports.Import;
import org.emftext.language.java.imports.ImportingElement;

import cipm.consistency.fitests.similarity.java.initialiser.imports.IImportingElementInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ImportingElement} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ImportingElement}
 * instances.
 */
public interface UsesImportingElements extends UsesImports {
	/**
	 * A variant of
	 * {@link #createMinimalImportingElement(IImportingElementInitialiser, Import)},
	 * where a {@link ClassifierImport} instance that points at a {@link Class}
	 * instance with clsName will be constructed. The said {@link Class} instance
	 * will also be constructed in the process.
	 * 
	 * @param clsName The name of the {@link Class} instance, to which the
	 *                {@link Import} will point at (that import will be added to the
	 *                constructed {@link ImportingElement} instance)
	 * 
	 * @see {@link #createMinimalClsImport(String)}
	 */
	public default ImportingElement createMinimalImportingElement(IImportingElementInitialiser init, String clsName) {
		return this.createMinimalImportingElement(init, this.createMinimalClsImport(clsName));
	}

	/**
	 * @param init The initialiser that will be used to construct the instance
	 * @param imp  The {@link Import} that will be added to the constructed instance
	 * @return An {@link ImportingElement} instance with the given parameters
	 */
	public default ImportingElement createMinimalImportingElement(IImportingElementInitialiser init, Import imp) {
		ImportingElement result = init.instantiate();
		init.addImport(result, imp);

		return result;
	}
}
