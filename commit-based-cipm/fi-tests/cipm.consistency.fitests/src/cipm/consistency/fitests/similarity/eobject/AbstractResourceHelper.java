package cipm.consistency.fitests.similarity.eobject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * An abstract class that is meant to be implemented by classes; which
 * encapsulate the means to modify {@link Resource} instances.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractResourceHelper {
	/**
	 * The map that keeps track of the mapping inserted into
	 * {@link Resource.Factory.Registry}. Can be used to clean such mappings from
	 * the registry at the end of tests.
	 */
	private final Map<String, Object> registryMappings = new HashMap<String, Object>();

	/**
	 * The list of created {@link Resource} instances. Can be used to perform clean
	 * up after tests.
	 */
	private final List<Resource> createdResources = new ArrayList<Resource>();

	/**
	 * The directory, where the created {@link Resource} instances will be stored,
	 * if they are saved.
	 */
	private Path resourceSaveRootPath;

	/**
	 * The extension of {@link Resource} files, if they are saved.
	 */
	private String resourceFileExtension;

	/**
	 * Constructs an instance with the foreseen initial resource registry entries.
	 * 
	 * @see {@link #setInitialResourceRegistries()}
	 */
	public AbstractResourceHelper() {
		this.setInitialResourceRegistries();
	}

	/**
	 * @return The resource registry, which will be modified by this instance.
	 */
	private Resource.Factory.Registry getResourceRegistry() {
		return Resource.Factory.Registry.INSTANCE;
	}

	/**
	 * @param key The registry key
	 * @param val The value of the corresponding registry (can be null)
	 * @return Whether the given resource registry key has been registered and has
	 *         the given value.
	 */
	public boolean resourceRegistryPresent(String key, Object val) {
		var regMap = this.getResourceRegistry().getExtensionToFactoryMap();
		var isKeyPresent = regMap.containsKey(key);

		Object valForKey = null;
		if (isKeyPresent) {
			valForKey = regMap.get(key);
		}

		return isKeyPresent && (valForKey == val || valForKey.equals(val));
	}

	/**
	 * @param key The registry key
	 * @return Whether the given resource registry key has been registered
	 */
	public boolean resourceRegistryPresent(String key) {
		return this.getResourceRegistry().getExtensionToFactoryMap().containsKey(key);
	}

	/**
	 * @return The {@link Logger} that can be used to log happenings in this
	 *         instance.
	 */
	protected Logger getLogger() {
		return Logger.getLogger("cipm." + this.getClass().getSimpleName());
	}

	/**
	 * Creates and returns a {@link Resource} instance, whose URI will be the given
	 * one. <br>
	 * <br>
	 * Does not save the created {@link Resource} instance.
	 */
	protected Resource initResource(URI resUri) {
		ResourceSet rSet = new ResourceSetImpl();
		return rSet.createResource(resUri);
	}

	/**
	 * Sets all resource registries foreseen for this instance. They are tracked and
	 * can be cleaned using {@link #cleanRegistry()}, if needed.
	 */
	public abstract void setInitialResourceRegistries();

	/**
	 * This method should indicate whether {@link #setInitialResourceRegistries()}
	 * has been called, or the desired resource registry state has been achieved
	 * without it.
	 * 
	 * @return Whether the necessary resource registries have been added
	 * 
	 * @see {@link #setInitialResourceRegistries()}
	 */
	public abstract boolean areRequiredResourceRegistriesPresent();

	/**
	 * Sets the directory, where the created {@link Resource} instances will be
	 * stored, if they are saved.
	 */
	public void setResourceSaveRootPath(Path resourceSaveRootPath) {
		this.resourceSaveRootPath = resourceSaveRootPath;
	}

	/**
	 * Sets the extension of {@link Resource} files, if they are saved.
	 */
	public void setResourceFileExtension(String resourceFileExtension) {
		this.resourceFileExtension = resourceFileExtension;
	}

	/**
	 * @return The extension of the {@link Resource} files.
	 */
	public String getResourceFileExtension() {
		return this.resourceFileExtension;
	}

	/**
	 * @return The directory, where the created {@link Resource} instances will be
	 *         stored, if they are saved.
	 */
	public Path getResourceSaveRootPath() {
		return resourceSaveRootPath;
	}

	/**
	 * Complements {@link #getResourceSaveRootPath()} with the {@link Resource} file
	 * name and extension. The said file will only be created, if the
	 * {@link Resource} file is saved.
	 * 
	 * @param resourceFileName      The name of the file
	 * @param resourceFileExtension The extension of the file
	 * @return The {@link URI} for a {@link Resource} instance.
	 */
	protected URI createURI(String resourceFileName, String resourceFileExtension) {
		return URI.createFileURI(
				this.getResourceSaveRootPath() + File.separator + resourceFileName + "." + resourceFileExtension);
	}

	/**
	 * The variant of {@link #createURI(String, String)}, which uses
	 * {@link #getResourceFileExtension()}.
	 */
	protected URI createURI(String resourceName) {
		return this.createURI(resourceName, this.getResourceFileExtension());
	}

	/**
	 * @return The name of the {@link Resource} file with the count parameter added
	 *         to it.
	 */
	protected String getResourceNameWithCount(String resourceName, int count) {
		return resourceName + "-" + count;
	}

	/**
	 * @return Computes a unique name for the {@link Resource} file, so that it is
	 *         not overwritten if another resource file with the same name is to be
	 *         created.
	 */
	protected String computeEffectiveResourceName(String resourceName) {
		var resourceRoot = this.getResourceSaveRootPath().toFile();

		var count = 0;

		if (resourceRoot.exists()) {
			var files = List.of(
					List.of(resourceRoot.listFiles()).stream().map((file) -> file.getName()).toArray(String[]::new));

			while (files.contains(this.getResourceNameWithCount(resourceName, count))) {
				count++;
			}
		}

		return this.getResourceNameWithCount(resourceName, count);
	}

	/**
	 * @return An empty {@link ResourceSetImpl}
	 */
	public ResourceSet createResourceSet() {
		return new ResourceSetImpl();
	}

	/**
	 * Creates a {@link Resource} instance within the given resource set rSet, for
	 * the given EObject instances eos (can be null), with the given URI resURI. The
	 * Resource instances created with this method are tracked, so that they can be
	 * deleted later if necessary.<br>
	 * <br>
	 * Note: The given URI will override {@link #getResourceSaveRootPath()} <br>
	 * <br>
	 * <b>!!! IMPORTANT !!!</b> <br>
	 * <br>
	 * <b>Using this method will cause the logger to log an error message, if some
	 * of the EObject instances (from eos) that are already in a Resource instance
	 * are attempted to be placed into another Resource. This should be avoided,
	 * since doing so will REMOVE the said EObject instances from their former
	 * Resource and cause side effects in tests.</b>
	 */
	public Resource createResource(Collection<? extends EObject> eos, ResourceSet rSet, URI resURI) {
		var res = rSet.createResource(resURI);
		this.createdResources.add(res);

		if (eos != null) {
			for (var eo : eos) {

				/*
				 * Make sure to not add an EObject, which has already been added to a Resource,
				 * to another Resource. Doing so will detach it from its former Resource and add
				 * it to the second one.
				 */
				if (eo.eResource() != null) {
					this.getLogger().error("An EObject's resource was set and shifted during resource creation");
				}
				res.getContents().add(eo);
			}
		}

		return res;
	}

	/**
	 * A variant of {@link #createResource(Collection, ResourceSet, URI)}, which
	 * computes the URI from given resourceName.
	 * 
	 * @see {@link #createURI(String)}
	 * @see {@link #computeEffectiveResourceName(String)}
	 */
	public Resource createResource(Collection<? extends EObject> eos, ResourceSet rSet, String resourceName) {
		return this.createResource(eos, rSet, this.createURI(this.computeEffectiveResourceName(resourceName)));
	}

	/**
	 * Adds the extension to factory mapping into {@link Resource.Factory.Registry}.
	 * The added entry will be tracked by this instance, allowing it to be removed
	 * if necessary. <br>
	 * <br>
	 * Said entry denotes that resources with the given extension are saved using
	 * the given factory.
	 * 
	 * @see {@link #setDefaultResourceRegistry()}
	 */
	public void setResourceRegistry(String extension, Object factory) {
		this.registryMappings.put(extension, factory);
		this.getResourceRegistry().getExtensionToFactoryMap().put(extension, factory);
	}

	/**
	 * Attempts to save the given resource instance. Instead of throwing exceptions,
	 * returns true/false to indicate success/failure.
	 */
	public boolean saveResource(Resource res) {
		var uri = res.getURI();
		if (uri.isFile()) {
			try {
				res.save(null);
				return this.resourceFileExists(uri);
			} catch (IOException excep) {
				excep.printStackTrace();
				return this.resourceFileExists(uri);
			}
		}
		return this.resourceFileExists(uri);
	}

	/**
	 * Attempts to save the given resource instance. Instead of throwing exceptions,
	 * returns true/false to indicate success/failure.
	 */
	public boolean saveResourceIfNotSaved(Resource res) {
		var uri = res.getURI();
		if (uri.isFile() && !this.resourceFileExists(uri)) {
			return this.saveResource(res);
		}
		return this.resourceFileExists(uri);
	}

	/**
	 * Attempts to save all resources created by this instance. Instead of throwing
	 * exceptions, returns true/false to indicate success/failure. Stops early, if
	 * saving a resource fails.
	 */
	public boolean saveAllResources() {
		var result = true;

		for (var res : this.createdResources) {
			if (result) {
				result = result && this.saveResource(res);
			}
		}

		return result;
	}

	/**
	 * Loads the given resource
	 */
	public void loadResource(Resource res) {
		try {
			this.getLogger().debug(String.format("Loading resource at: %s", res.getURI()));
			res.load(null);
			this.getLogger().debug(String.format("Loaded %s", res.getURI()));
		} catch (IOException e) {
			e.printStackTrace();
			this.getLogger().debug(String.format("Could not load resource at: %s", res.getURI()));
		}
	}

	/**
	 * @return A resource instance, which has the contents of the saved resource
	 *         file at the given URI
	 */
	public Resource loadResource(URI resourceURI) {
		Resource res = null;

		if (resourceURI.isFile() && new File(resourceURI.toFileString()).exists()) {
			res = this.createResource(resourceURI);
			this.loadResource(res);
		}

		return res;
	}

	/**
	 * @return The loaded resource located at the given path
	 */
	public Resource loadResource(Path resourcePath) {
		return this.loadResource(URI.createFileURI(resourcePath.toString()));
	}

	/**
	 * @param resSet      The resource ste, which will contain the created resource
	 * @param resourceURI The URI, where the resource points at
	 * @return An empty resource inside the given resource set, with the given URI
	 */
	public Resource createResource(ResourceSet resSet, URI resourceURI) {
		return this.createResource(null, resSet, resourceURI);
	}

	/**
	 * @param resourceURI The URI, where the resource points at
	 * @return An empty resource, inside a freshly created resource set, with the
	 *         given URI
	 */
	public Resource createResource(URI resourceURI) {
		return this.createResource(this.createResourceSet(), resourceURI);
	}

	/**
	 * Unloads the given {@link Resource} instance.
	 */
	public boolean unloadResource(Resource res) {
		res.unload();
		return !res.isLoaded();
	}

	/**
	 * Unloads all resources created by this instance.
	 */
	public void unloadAllResources() {
		this.createdResources.forEach((r) -> this.unloadResource(r));
	}

	public boolean resourceFileExists(URI resURI) {
		return resURI.isFile() && new File(resURI.toFileString()).exists();
	}

	/**
	 * Deletes the given resource
	 */
	public boolean deleteResource(Resource res) {
		var uri = res.getURI();
		if (this.resourceFileExists(uri)) {
			try {
				res.delete(null);
				return !this.resourceFileExists(uri);
			} catch (IOException e) {
				e.printStackTrace();
				this.getLogger().debug("Could not delete resource: " + res.getURI().toString());
				return !this.resourceFileExists(uri);
			}
		}
		return !this.resourceFileExists(uri);
	}

	/**
	 * Unloads and deletes all created {@link Resource} instances, if they are
	 * created with {@link #createResource(Collection)}. Stops tracking them as
	 * well.
	 */
	public void deleteAllResources() {
		this.createdResources.forEach((r) -> {
			this.unloadResource(r);

			if (r.getURI().isFile() && new File(r.getURI().toFileString()).exists()) {
				try {
					r.delete(null);
				} catch (IOException e) {
					e.printStackTrace();
					this.getLogger().debug("Could not delete resource: " + r.getURI().toString());
				}
			}
		});

		this.createdResources.clear();
	}

	/**
	 * Deletes the directory that contains all {@link Resource} instances, if it is
	 * empty.
	 */
	public void deleteResourceDir() {
		this.getResourceSaveRootPath().toFile().delete();
	}

	/**
	 * Removes the entry matching to the given {@code resourceFileExtension} from
	 * the resource factory, if it was added by this instance. Stops tracking the
	 * said entry.
	 */
	public void removeFromRegistry(String resourceFileExtension) {
		if (resourceFileExtension == null)
			return;

		var regMap = this.getResourceRegistry().getExtensionToFactoryMap();

		if (regMap.containsKey(resourceFileExtension)) {
			var val = regMap.get(resourceFileExtension);

			if (this.registryMappings.containsKey(resourceFileExtension)) {
				var valTracked = this.registryMappings.get(resourceFileExtension);

				if (val.equals(valTracked)) {
					regMap.remove(val);
					this.registryMappings.remove(valTracked);
				}
			}
		}
	}

	/**
	 * Cleans the mapping(s) in {@link Resource.Factory.Registry} inserted by
	 * {@link #setResourceRegistry(String)}. Stops tracking them as well.
	 */
	public void cleanRegistry() {
		for (var key : this.registryMappings.keySet()) {
			this.getResourceRegistry().getExtensionToFactoryMap().remove(key);
		}

		this.registryMappings.clear();
	}
}
