package cipm.consistency.fitests.similarity.eobject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

/**
 * A class that encapsulates the means to create {@link Resource} instances.
 * <br>
 * <br>
 * Uses {@link XMIResourceFactoryImpl} as Resource factory for all Resource
 * extensions.
 * 
 * @author Alp Torac Genc
 */
public class DefaultResourceHelper extends AbstractResourceHelper {
	/**
	 * Pattern that matches all Resource file extensions
	 */
	private static final String defaultFactoryKey = "*";

	public DefaultResourceHelper() {
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
}
