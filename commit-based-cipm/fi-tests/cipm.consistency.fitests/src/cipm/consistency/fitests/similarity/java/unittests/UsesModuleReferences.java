package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.modules.ModuleReference;

import cipm.consistency.fitests.similarity.java.initialiser.modules.ModuleReferenceInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ModuleReference} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ModuleReference}
 * instances.
 */
public interface UsesModuleReferences extends UsesModules {

	/**
	 * A variant of {@link #createMinimalModule(String, String[])}, where the
	 * {@link Module} constructed in the process has no namespaces.
	 * 
	 * @param modName The name of the {@link Module}, at which the constructed
	 *                instance will point
	 */
	public default ModuleReference createMinimalMR(String modName) {
		return this.createMinimalMR(modName, null);
	}

	/**
	 * @param modName   The name of the {@link Module}, at which the constructed
	 *                  instance will point
	 * @param modRefNss The namespaces of the {@link Module}, at which the
	 *                  constructed instance will point
	 * @return A {@link ModuleReference} instance that points at a constructed
	 *         {@link Module} with the given parameters
	 * 
	 * @see {@link #createMinimalModule(String)}
	 */
	public default ModuleReference createMinimalMR(String modName, String[] modRefNss) {
		var mrInit = new ModuleReferenceInitialiser();
		var mr = mrInit.instantiate();
		mrInit.setTarget(mr, this.createMinimalModule(modName));
		mrInit.addNamespaces(mr, modRefNss);
		return mr;
	}
}
