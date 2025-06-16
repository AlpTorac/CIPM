package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import cipm.consistency.fitests.similarity.eobject.AbstractResourceHelper;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;
import jamopp.recovery.trivial.TrivialRecovery;

/**
 * A class that wraps a model resource, which is either already parsed or is to
 * be parsed. It encapsulates the desired model resource, as well as all other
 * resources it requires:
 * <ul>
 * <li>Merged model resource: Contains all direct contents of the model files
 * (i.e. the Java code directly present in model files)
 * <li>Artificial resource: Contains all contents that are required by the
 * merged model resource, but are not directly present in model files, such as
 * contents of native Java libraries and synthetic elements.
 * </ul>
 * The main purpose of this class is to make operations on parsed model
 * resources, and other resources parsed in the process, tidier.
 * 
 * @author Alp Torac Genc
 */
public class ModelResourceWrapper implements IModelResourceWrapper {
	private AbstractResourceHelper resHelper;
	private static final Logger logger = Logger.getLogger(ModelResourceWrapper.class);

	/**
	 * The name of the ArtificialResource (i.e. the last segment of its URI) without
	 * file extension
	 */
	private static final String artificialResourceName = "ArtificialResource";

	/**
	 * @see {@link #ModelResourceWrapper(AbstractResourceHelper, JaMoPPJDTSingleFileParser)}
	 */
	private JaMoPPJDTSingleFileParser parser;

	/**
	 * @see {@link #getModelResource()}
	 */
	private Resource mergedModelResource;
	/**
	 * The artificial resource, which contains all contents required by the merged
	 * model resource that were not directly present in the model files
	 */
	private Resource artificialResource;

	/**
	 * Constructs an instance.
	 * 
	 * @param resHelper An object that helps with Resource-related operations
	 * @param parser    The parser that will be used to parse the model resource and
	 *                  all other necessary resources
	 */
	public ModelResourceWrapper(AbstractResourceHelper resHelper, JaMoPPJDTSingleFileParser parser) {
		this.resHelper = resHelper;
		this.parser = parser;
	}

	/**
	 * Constructs an instance.
	 * 
	 * @param resHelper An object that helps with Resource-related operations
	 */
	public ModelResourceWrapper(AbstractResourceHelper resHelper) {
		this(resHelper, null);
	}

	/**
	 * @param correspondingResourceFileNameWithoutExt The name of the model
	 *                                                resource, whose corresponding
	 *                                                ArtificialResource's name
	 *                                                (without file extension) is to
	 *                                                be computed
	 * @return The name of the ArtificialResource corresponding to the model
	 *         resource with the given name.
	 */
	protected String getArtificialResourceFileName(String correspondingResourceFileNameWithoutExt) {
		return correspondingResourceFileNameWithoutExt + artificialResourceName + "."
				+ this.resHelper.getResourceFileExtension();
	}

	/**
	 * @param correspondingResourceURI The URI of the model resource, whose
	 *                                 ArtificialResource's URI is to be computed
	 * @return The URI of the ArtificialResource of the model resource with the
	 *         given URI
	 */
	protected URI getArtificialResourceURI(URI correspondingResourceURI) {
		var fileNameWithoutExt = correspondingResourceURI.trimFileExtension().lastSegment();
		var arName = this.getArtificialResourceFileName(fileNameWithoutExt);
		var arURI = correspondingResourceURI.trimSegments(1);
		return arURI.appendSegment(arName);
	}

	/**
	 * Creates and prepares the ArtificialResource of the given model resource,
	 * under the same ResourceSet (i.e. the ResourceSet of the given model
	 * resource). The created ArtificialResource will contain synthetic model
	 * elements for the proxy objects within the given model resource, as well as
	 * the native Java library resources that are needed by the given model
	 * resource. <br>
	 * <br>
	 * Separating said model elements from the model resource allows its actual
	 * contents (i.e. the code that is directly present in the model files) to be
	 * compared more efficiently, by excluding the imported dependencies, which are
	 * the same for each model resource.
	 * 
	 * @param modelResource         The model resource, for which an
	 *                              ArtificialResource should be created
	 * @param artificialResourceURI The URI, which the created ArtificialResource
	 *                              will have
	 * @return The created ArtificialResource
	 */
	protected Resource prepareArtificialResource(Resource modelResource, URI artificialResourceURI) {
		var modelResourceSet = modelResource.getResourceSet();

		// Create the ArtificialResource
		new TrivialRecovery(modelResourceSet).recover();

		var artificialResource = modelResourceSet.getResources().stream()
				.filter((r) -> r.getURI().toString().contains(artificialResourceName)).findFirst().orElse(null);

		if (artificialResource != null) {
			artificialResource.setURI(artificialResourceURI);

			logger.debug(String.format("ArtificialResource is parsed and has its URI set to %s",
					artificialResource.getURI()));

			// Use an array to avoid modifications while iterating, which lead to exceptions
			var resArr = modelResourceSet.getResources().toArray(Resource[]::new);

			/*
			 * Iterate over all resources under modelResourceSet and look for resources of
			 * native Java libraries. Place each such resource's contents into the
			 * ArtificialResource and remove the native Java library resource from
			 * modelResourceSet (as it will be empty afterward). This moves all
			 * CompilationUnits housing the Classifiers required by the parsed model
			 * resource into ArtificialResource.
			 */
			for (int i = 0; i < resArr.length; i++) {
				var r = resArr[i];
				if (!r.getURI().isFile() && r != artificialResource && r != modelResource) {
					logger.debug(String.format("Adding Resource %s to ArtificialResource", r.getURI()));
					artificialResource.getContents().addAll(r.getContents());
					logger.debug(String.format("Added Resource %s to ArtificialResource", r.getURI()));
					modelResourceSet.getResources().remove(r);
					logger.debug(String.format("Removed (empty) Resource %s from ResourceSet", r.getURI()));
				}
			}

			// "-2" to exclude modelResource and artificialResource from resource count
			logger.debug(String.format("%d/%d resources have been added to ArtificialResource",
					(resArr.length - modelResourceSet.getResources().size()) - 2, resArr.length - 2));

			// Do not handle potential proxies in ArtificialResource, because they belong to
			// internals of native classes, which are irrelevant for the model. Normally
			// there should be no proxies, if the code represented in the model resource is
			// valid.
		}

		return artificialResource;
	}

	/**
	 * @return The parser that is currently used to parse model resources
	 */
	public JaMoPPJDTSingleFileParser getModelResourceParser() {
		return this.parser;
	}

	/**
	 * Sets the parser that will be used to parse model resources
	 */
	public void setModelResourceParser(JaMoPPJDTSingleFileParser parser) {
		this.parser = parser;
	}

	/**
	 * Parses all Java-Model files under the given directory into a {@link Resource}
	 * instance (merged model resource). Uses no means of caching. The parsed merged
	 * model resource can be accessed via {@link #getModelResource()}. <br>
	 * <br>
	 * <b>Note: This method will parse ALL such files. Therefore, the given model
	 * directory should only contain one Java-Model.</b>
	 * 
	 * @param modelDir         A directory that contains all files of a model
	 * @param modelResourceURI The URI that the parsed model resource will reside
	 *                         at, once saved
	 */
	@Override
	public void parseModelResource(Path modelDir, URI modelResourceURI) {
		var modelResourceSet = this.resHelper.createResourceSet();

		parser.setResourceSet(modelResourceSet);

		// Parser returns the same ResourceSet it was previously given
		// via setResourceSet(...)
		modelResourceSet = parser.parseDirectory(modelDir);

		var resCount = modelResourceSet.getResources().size();
		logger.debug(String.format("%d resources have been parsed under %s", resCount, modelDir));

		// Find the model resource (i.e. the resource that contains the direct contents
		// of model files)
		var modelResource = modelResourceSet.getResources().stream()
				.filter((r) -> r.getURI().toFileString().contains(modelDir.toString())).findFirst().get();

		/*
		 * Attempt to resolve potential proxies that can be resolved prior to
		 * TrivialRecovery, so that it constructs less synthetic elements that are
		 * redundant.
		 * 
		 * This is necessary, because synthetic elements' type can vary and can cause
		 * typing issues during similarity checking, as the (cached) model resource will
		 * use the synthetic elements, even though they are present directly in the
		 * model resource.
		 * 
		 * Examples to this are LocalVariableStatements; which are declared within the
		 * model, are accessible and referenced by IdentifierReferences. Due to the
		 * absence of context information during parsing, they are considered Fields,
		 * unless they are resolved (via EcoreUtil.resolveAll(...) for instance)
		 * directly after being parsed. Not resolving them causes the
		 * IdentifierReferences to point at their synthetic element correspondents
		 * (Fields), as opposed to their declaration in the model resource.
		 */
		EcoreUtil.resolveAll(modelResource);

		mergedModelResource = this.resHelper.createResource(modelResourceURI);

		artificialResource = this.prepareArtificialResource(modelResource,
				this.getArtificialResourceURI(modelResourceURI));

		logger.debug(String.format("Merging non-ArtificialResources"));

		for (var r : modelResourceSet.getResources()) {
			if (r != artificialResource) {
				logger.debug(String.format("Including %s into the merged resource", r.getURI()));
				mergedModelResource.getContents().addAll(r.getContents());
				logger.debug(String.format("Included %s into the merged resource", r.getURI()));
			}
		}

		logger.debug(String.format("Merged non-ArtificialResources"));

		logger.debug(String.format("%s parsed (uncached)", modelDir));

		// Add ArtificialResource to mergedResource's resource set, so that it can be
		// found by the model resource's contents that have been moved
		if (artificialResource != null) {
			mergedModelResource.getResourceSet().getResources().add(artificialResource);
		}
	}

	/**
	 * Loads the merged model resource that was previously parsed.
	 * 
	 * @param modelResourceURI The URI, at which a previously parsed merged model
	 *                         resource resides
	 */
	public void loadModelResource(URI modelResourceURI) {
		this.mergedModelResource = this.resHelper.loadResource(modelResourceURI);
	}

	/**
	 * @return Whether all parsed resources have been saved. If no resources have
	 *         been parsed, no resources will be saved.
	 */
	public boolean saveResources() {
		var result = true;
		if (mergedModelResource != null) {
			logger.debug("Merged model resource exists, saving it now");
			result = this.resHelper.saveResourceIfNotSaved(mergedModelResource);
			logger.debug(String.format("%s merged model resource at %s", result ? "Saved" : "Could not save",
					mergedModelResource.getURI()));
		}
		if (artificialResource != null) {
			logger.debug("Artificial resource exists, saving it now");
			result = result && this.resHelper.saveResourceIfNotSaved(artificialResource);
			logger.debug(String.format("%s artificial resource at %s", result ? "Saved" : "Could not save",
					artificialResource.getURI()));
		}
		return result;
	}

	/**
	 * @return Whether all parsed resources have been deleted. If no resources have
	 *         been parsed, no resource will be deleted.
	 */
	public boolean deleteResources() {
		var result = false;
		if (mergedModelResource != null) {
			logger.debug("Merged model resource exists, deleting it now");
			result = this.resHelper.deleteResource(mergedModelResource);
			logger.debug(String.format("%s merged model resource at %s", result ? "Deleted" : "Could not delete",
					mergedModelResource.getURI()));

		}
		if (artificialResource != null) {
			logger.debug("Artificial resource exists, deleting it now");
			result = result && this.resHelper.deleteResource(artificialResource);
			logger.debug(String.format("%s artificial resource at %s", result ? "Saved" : "Could not save",
					artificialResource.getURI()));
		}
		return result;
	}

	/**
	 * @return Whether all parsed resources have been unloaded. If no resources have
	 *         been parsed, no resource will be unloaded.
	 */
	public boolean unloadResources() {
		var result = false;
		if (mergedModelResource != null) {
			logger.debug("Merged model resource exists, unloading it now");
			result = this.resHelper.unloadResource(mergedModelResource);
			logger.debug(String.format("%s merged model resource at %s", result ? "Unloaded" : "Could not unload",
					mergedModelResource.getURI()));

		}
		if (artificialResource != null) {
			logger.debug("Artificial resource exists, unloading it now");
			result = result && this.resHelper.unloadResource(artificialResource);
			logger.debug(String.format("%s artificial resource at %s", result ? "Unloaded" : "Could not unload",
					artificialResource.getURI()));
		}
		return result;
	}

	/**
	 * Only checks whether the merged model resource is loaded, because other
	 * resources will be automatically loaded on demand.
	 * 
	 * @return Whether the merged model resource is loaded
	 */
	public boolean isModelResourceLoaded() {
		return this.mergedModelResource != null && this.mergedModelResource.isLoaded();
	}

	/**
	 * @return Whether all parsed model resources have been loaded. If no resources
	 *         have been parsed, no resource will be loaded.
	 */
	public boolean loadParsedResources() {
		var result = true;
		if (mergedModelResource != null && !this.mergedModelResource.isLoaded()) {
			logger.debug("Merged model resource exists, loading it now");
			result = this.resHelper.unloadResource(mergedModelResource);
			logger.debug(String.format("%s merged model resource at %s", result ? "Loaded" : "Could not load",
					mergedModelResource.getURI()));

		}
		if (artificialResource != null && !this.artificialResource.isLoaded()) {
			logger.debug("Artificial resource exists, unloading it now");
			result = result && this.resHelper.unloadResource(artificialResource);
			logger.debug(String.format("%s artificial resource at %s", result ? "Loaded" : "Could not load",
					artificialResource.getURI()));
		}
		return result;
	}

	/**
	 * Sets the URIs of all parsed resources with respect to the given URI, which
	 * will be assigned to the parsed model resource that contains all direct
	 * contents of the model files.
	 * 
	 * @param newParsedModelResourceURI The new URI of the parsed model resource
	 *                                  ({@link #getModelResource()} in this case)
	 */
	public void setModelResourcesURI(URI newParsedModelResourceURI) {
		if (mergedModelResource != null) {
			mergedModelResource.setURI(newParsedModelResourceURI);
		}
		if (artificialResource != null) {
			artificialResource.setURI(this.getArtificialResourceURI(newParsedModelResourceURI));
		}
	}

	/**
	 * @return The merged model resource, which contains all contents of all
	 *         (directly) parsed model files
	 */
	public Resource getModelResource() {
		return mergedModelResource;
	}

	/**
	 * @return Whether there is a parsed model resource
	 *         ({@link #getModelResource()}) saved in this instance.
	 */
	public boolean modelResourceExists() {
		return this.getModelResource() != null;
	}
}
