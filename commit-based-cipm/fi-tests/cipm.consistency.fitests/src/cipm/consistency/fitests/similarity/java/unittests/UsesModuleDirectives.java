package cipm.consistency.fitests.similarity.java.unittests;

import org.emftext.language.java.containers.Package;
import org.emftext.language.java.modules.ExportsModuleDirective;
import org.emftext.language.java.modules.OpensModuleDirective;

import cipm.consistency.fitests.similarity.java.initialiser.modules.ExportsModuleDirectiveInitialiser;
import cipm.consistency.fitests.similarity.java.initialiser.modules.OpensModuleDirectiveInitialiser;

/**
 * An interface that can be implemented by tests, which work with
 * {@link ModuleDirective} instances. <br>
 * <br>
 * Contains methods that can be used to create {@link ModuleDirective}
 * instances.
 */
public interface UsesModuleDirectives extends UsesPackages, UsesModuleReferences {
	/**
	 * @param pac The accessable package of the instance to be constructed
	 * @return An {@link ExportsModuleDirective} instance with the given parameter
	 */
	public default ExportsModuleDirective createMinimalEMD(Package pac) {
		var init = new ExportsModuleDirectiveInitialiser();
		ExportsModuleDirective result = init.instantiate();
		init.setAccessablePackage(result, pac);
		return result;
	}

	/**
	 * @param pac The accessable package of the instance to be constructed
	 * @return An {@link OpensModuleDirective} instance with the given parameter
	 */
	public default OpensModuleDirective createMinimalOMD(Package pac) {
		var init = new OpensModuleDirectiveInitialiser();
		OpensModuleDirective result = init.instantiate();
		init.setAccessablePackage(result, pac);
		return result;
	}

	/**
	 * A variant of {@link #createMinimalEMD(Package)}, where a minimal
	 * {@link Package} instance is constructed and used.
	 * 
	 * @param nss The namespaces of the {@link Package} to be constructed in the
	 *            process
	 * 
	 * @see {@link #createMinimalPackage(String[])}
	 */
	public default ExportsModuleDirective createMinimalEMD(String[] nss) {
		return this.createMinimalEMD(this.createMinimalPackage(nss));
	}

	/**
	 * A variant of {@link #createMinimalOMD(Package)}, where a minimal
	 * {@link Package} instance is constructed and used.
	 * 
	 * @param nss The namespaces of the {@link Package} to be constructed in the
	 *            process
	 * 
	 * @see {@link #createMinimalPackage(String[])}
	 */
	public default OpensModuleDirective createMinimalOMD(String[] nss) {
		return this.createMinimalOMD(this.createMinimalPackage(nss));
	}
}
