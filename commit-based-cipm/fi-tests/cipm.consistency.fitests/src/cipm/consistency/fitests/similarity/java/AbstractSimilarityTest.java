package cipm.consistency.fitests.similarity.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

/**
 * The abstract test class that contains test elements needed in similarity
 * checking tests.
 * 
 * @author atora
 * @see {@link EObjectSimilarityTest}
 */
public abstract class AbstractSimilarityTest {
	/**
	 * The directory, where the created {@link Resource} instances will be stored,
	 * if they are saved.
	 */
	private static final String resourceRootPath = new File("").getAbsoluteFile().getAbsolutePath() + File.separator
			+ "testModels";

	/**
	 * The extension of {@link Resource} files, if they are saved.
	 */
	private final String extension = "javaxmi";

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
	 * The similarity checker that will handle similarity checking operations.
	 */
	private ISimilarityChecker sc;

	/**
	 * The name of the current test class.
	 */
	private String testPrefix = "";
	/**
	 * The name of the current test method.
	 */
	private String testIdentifier = "";

	@BeforeEach
	public void setUp() {
		this.setResourceFileTestPrefix(this.getClass().getSimpleName());

		this.setUpLogger();
		this.setResourceRegistry(this.getResourceRootPath());
		this.setSimilarityChecker(this.initSC());
	}

	@AfterEach
	public void tearDown() {
		this.cleanRegistry();
		this.cleanAllResources();
		this.deleteResourceDir();
	}

	/**
	 * @return The logger of the current test class.
	 */
	protected Logger getLogger() {
		return Logger.getLogger("cipm." + this.getClass().getSimpleName());
	}

	/**
	 * Creates and returns a {@link Resource} instance, whose uri will be the given
	 * one.
	 */
	private Resource initResource(URI uri) {
		ResourceSet rSet = new ResourceSetImpl();
		return rSet.createResource(uri);
	}

	/**
	 * Prepares loggers. <br>
	 * <br>
	 * <b>Enabling too many loggers (without limiting the console size) can cause
	 * Java memory issues.</b>
	 */
	protected void setUpLogger() {
		Logger logger = Logger.getLogger("cipm");
		logger.setLevel(Level.ALL);
//		logger = Logger.getLogger("jamopp");
//		logger.setLevel(Level.ALL);
		logger = Logger.getRootLogger();
		logger.removeAllAppenders();
		ConsoleAppender ap = new ConsoleAppender(new PatternLayout("[%d{DATE}] %-5p: %c - %m%n"),
				ConsoleAppender.SYSTEM_OUT);
		logger.addAppender(ap);
	}

	/**
	 * Creates the concrete {@link ISimilarityChecker} that will be used in tests.
	 * Can be overridden to change the said similarity checker.
	 * 
	 * @return A {@link ISimilarityChecker} implementation.
	 */
	protected ISimilarityChecker initSC() {
		return new JavaSimilarityCheckerProvider().createSC();
	}

	/**
	 * Sets the used {@link ISimilarityChecker} to the given one.
	 */
	protected void setSimilarityChecker(ISimilarityChecker sc) {
		this.sc = sc;
	}

	/**
	 * @return The extension of the {@link Resource} files.
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * @return The directory, where the created {@link Resource} instances will be
	 *         stored, if they are saved.
	 */
	public static String getAbstractSimilarityTestResourceRootPath() {
		return resourceRootPath;
	}

	/**
	 * The non-static version of
	 * {@link #getAbstractSimilarityTestResourceRootPath()}.
	 */
	public String getResourceRootPath() {
		return getAbstractSimilarityTestResourceRootPath();
	}

	/**
	 * Complements {@link #getResourceRootPath()} with the {@link Resource} file
	 * name and extension. The said file will only be created, if the
	 * {@link Resource} file is saved.
	 * 
	 * @param resourceFileName      The name of the file
	 * @param resourceFileExtension The extension of the file
	 * @return The {@link URI} for a {@link Resource} instance.
	 */
	public URI createURI(String resourceFileName, String resourceFileExtension) {
		return URI.createFileURI(
				this.getResourceRootPath() + File.separator + resourceFileName + "." + resourceFileExtension);
	}

	/**
	 * The variant of {@link #createURI(String, String)}, which uses
	 * {@link #getExtension()}.
	 */
	public URI createURI(String resourceName) {
		return this.createURI(resourceName, this.getExtension());
	}

	/**
	 * Provides a unique name for the file of the {@link Resource} instance, in
	 * order to avoid them from replacing one another, should multiple
	 * {@link Resource} instances be saved in the same test.
	 * 
	 * @return A unique name for the file of the {@link Resource} instance.
	 */
	public String getResourceName() {
		var count = 1;

		var prefix = this.getResourceFileTestPrefix() + "_" + this.getResourceFileTestIdentifier();

		var resourceRoot = new File(this.getResourceRootPath());

		if (resourceRoot.exists()) {
			count = resourceRoot.listFiles((f) -> f.getName().equals(prefix)).length + count;
		}

		return prefix + "_" + count;
	}

	/**
	 * Creates a {@link Resource} instance for the given EObject instances. <br>
	 * <br>
	 * <b>!!! IMPORTANT !!!</b> <br>
	 * <br>
	 * <b>Using this method will cause {@link AbstractSimilarityTest#LOGGER} to send
	 * an error message, if some of the EObject instances (from eos) that are
	 * already in a Resource instance are attempted to be placed into another
	 * Resource. This should be avoided, since doing so will REMOVE the said EObject
	 * instances from their former Resource and cause side effects in tests.</b>
	 */
	protected Resource createResource(Collection<? extends EObject> eos) {
		Resource res = this.initResource(this.createURI(this.getResourceName()));
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
	 * Puts the necessary mapping for saving {@link Resource} files with the given
	 * file extension into {@link Resource.Factory.Registry}. Duplicates the entry
	 * and adds it to {@link #registryMappings} as well.
	 */
	public void setResourceRegistry(String extension) {
		this.registryMappings.put(extension, new XMIResourceFactoryImpl());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(extension, new XMIResourceFactoryImpl());
	}

	/**
	 * Unloads the given {@link Resource} instance.
	 */
	public void unloadResource(Resource res) {
		res.unload();
	}

	/**
	 * Unloads and removes all created {@link Resource} instances, if they are
	 * created with {@link #createResource(Collection)}.
	 */
	public void cleanAllResources() {
		this.createdResources.forEach((r) -> {
			this.unloadResource(r);

			try {
				r.delete(null);
			} catch (IOException e) {
				this.getLogger().debug("Resource either was not created as a file or has already been deleted: "
						+ r.getURI().toString());
			}
		});

		this.createdResources.clear();
	}

	/**
	 * Deletes the directory that contains all {@link Resource} instances, if it is
	 * empty.
	 */
	public void deleteResourceDir() {
		new File(this.getResourceRootPath()).delete();
	}

	/**
	 * Cleans the mapping(s) in {@link Resource.Factory.Registry} inserted by
	 * {@link #setResourceRegistry(String)}.
	 */
	public void cleanRegistry() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;

		for (var key : this.registryMappings.keySet()) {
			reg.getExtensionToFactoryMap().remove(key);
		}

		this.registryMappings.clear();
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.sc.isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	@SuppressWarnings("unchecked")
	public Boolean areSimilar(Collection<? extends EObject> elements1, Collection<? extends EObject> elements2) {
		return this.sc.areSimilar((Collection<Object>) elements1, (Collection<Object>) elements2);
	}

	/**
	 * @return The prefix of the {@link Resource} file names created from within the
	 *         current test class. Defaults to the name of the current test class.
	 * 
	 * @see {@link #getResourceName()}
	 */
	public String getResourceFileTestPrefix() {
		return this.testPrefix;
	}

	/**
	 * @return The unique part of the {@link Resource} file names created from
	 *         within the current test class. Defaults to the name of the current
	 *         test method.
	 * 
	 * @see {@link #getResourceName()}
	 */
	public String getResourceFileTestIdentifier() {
		return this.testIdentifier;
	}

	/**
	 * Sets the value that will be returned by {@link #getResourceFileTestPrefix()}.
	 */
	protected void setResourceFileTestPrefix(String testPrefix) {
		this.testPrefix = testPrefix;
	}

	/**
	 * Sets the value that will be returned by
	 * {@link #getResourceFileTestIdentifier()}.
	 */
	protected void setResourceFileTestIdentifier(String testIdentifier) {
		this.testIdentifier = testIdentifier;
	}
}
