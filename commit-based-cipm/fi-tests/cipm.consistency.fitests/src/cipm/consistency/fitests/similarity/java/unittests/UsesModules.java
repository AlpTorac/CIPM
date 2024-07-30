package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Module;

import cipm.consistency.fitests.similarity.java.initialiser.containers.ModuleInitialiser;

/**
 * An interface that can be implemented by tests, which work with {@link Module}
 * instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Module} instances.
 */
public interface UsesModules {
	/**
	 * A variation of {@link #createMinimalModule(String, String[])}, where the
	 * constructed instance has no namespaces.
	 */
	public default Module createMinimalModule(String name) {
		return this.createMinimalModule(name, null);
	}

	/**
	 * @param name The name of the instance to be constructed
	 * @param nss  The namespaces of the instance to be constructed
	 * @return A {@link Module} instance with the given parameters
	 */
	public default Module createMinimalModule(String name, String[] nss) {
		var modInit = new ModuleInitialiser();
		var mod = modInit.instantiate();
		modInit.setName(mod, name);
		modInit.addNamespaces(mod, nss);
		return mod;
	}
}
