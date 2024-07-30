package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.imports.PackageImport;

import cipm.consistency.fitests.similarity.java.initialiser.imports.PackageImportInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link PackageImport} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link PackageImport} instances.
 */
public interface UsesPackageImports {
	/**
	 * @param nss The namespaces of the instance to be constructed
	 * @return A {@link PackageImport} instance with the given parameters
	 */
	public default PackageImport createMinimalPackageImport(String[] nss) {
		var initialiser = new PackageImportInitialiser();
		var result = initialiser.instantiate();
		initialiser.addNamespaces(result, nss);

		return result;
	}
}