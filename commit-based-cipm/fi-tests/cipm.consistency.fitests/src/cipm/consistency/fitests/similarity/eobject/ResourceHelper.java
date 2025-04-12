package cipm.consistency.fitests.similarity.eobject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * A class that encapsulates the means to create {@link Resource} instances.
 * 
 * @author Alp Torac Genc
 */
public class ResourceHelper extends AbstractResourceHelper {
	private static final String defaultFactoryKey = "*";

	public ResourceHelper() {
		super();
		this.setResourceFileExtension(defaultFactoryKey);
	}

	/**
	 * Adds the mapping into {@link Resource.Factory.Registry} for saving resource
	 * instances using XMI format.
	 * 
	 * @see {@link #setResourceRegistry(String, Object)}
	 */
	@Override
	public void setInitialResourceRegistries() {
		this.setResourceRegistry(defaultFactoryKey, new XMIResourceFactoryImpl());
	}

	@Override
	public boolean areRequiredResourceRegistriesPresent() {
		return this.resourceRegistryPresent(defaultFactoryKey);
	}
}
