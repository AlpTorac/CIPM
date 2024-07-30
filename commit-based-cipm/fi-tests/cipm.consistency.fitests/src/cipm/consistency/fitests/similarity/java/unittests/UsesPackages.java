package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Package;

import cipm.consistency.fitests.similarity.java.initialiser.containers.PackageInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link Package} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link Package} instances.
 */
public interface UsesPackages {
	/**
	 * @param nss The namespaces of the instance to be constructed
	 * @return A {@link Package} instance with the given parameters
	 */
	public default Package createMinimalPackage(String[] nss) {
		var pacInit = new PackageInitialiser();

		Package result = pacInit.instantiate();
		pacInit.addNamespaces(result, nss);

		return result;
	}

	/**
	 * A variant of {@link #createMinimalPackage(String[])}, where namespaces are
	 * generated using the given parameters. <br>
	 * <br>
	 * The generated namespaces will each have the given prefix. As suffix, the
	 * namespaces will have a number between 0 (including) and the given count
	 * (excluding). <br>
	 * <br>
	 * Example: {@code nsPrefix = "ns", nsCount = 3} constructs a {@link Package}
	 * instance with namespaces "ns0", "ns1", "ns2".
	 * 
	 * @param nsPrefix The prefix of the namespaces to be generated
	 * @param nsCount  The count of the namespaces to be generated
	 */
	public default Package createMinimalPackage(String nsPrefix, int nsCount) {
		var nss = new String[nsCount];

		for (int i = 0; i < nsCount; i++)
			nss[i] = nsPrefix + i;

		return this.createMinimalPackage(nss);
	}
}
