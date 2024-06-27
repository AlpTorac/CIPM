package cipm.consistency.fitests.similarity.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.splevo.jamopp.diffing.similarity.base.MapSimilarityToolboxFactory;

import cipm.consistency.fitests.similarity.java.utils.DummySimilarityChecker;
import cipm.consistency.fitests.similarity.java.utils.DummySimilarityToolboxBuilder;
import cipm.consistency.fitests.similarity.java.utils.InnerSwitchFactory;

public abstract class AbstractSimilarityTest {
	private static final String resourceRootPath = new File("").getAbsoluteFile().getAbsolutePath()
			+ File.separator + "testModels";
	
	private final String extension = "javaxmi";
	
	private final List<Resource> createdResources = new ArrayList<Resource>();
	
	private boolean defaultCheckStatementPosition = true;
	
	// TODO: Remove inner switches and other dummy elements
	private DummySimilarityChecker sc;
	
	private String testPrefix = "";
	private String testIdentifier = "";
	
	@BeforeEach
	public void setUp() {
		this.setResourceFileTestPrefix(this.getClass().getSimpleName());
		
		this.setUpLogger();
		this.setResourceRegistry(this.getResourceRootPath());
		this.sc = this.initSC();
	}
	
	@AfterEach
	public void tearDown() {
		this.cleanRegistry();
		this.cleanAllResources();
		this.deleteResourceDir();
	}
	
	protected Logger getLogger() {
		return Logger.getLogger("cipm."+this.getClass().getSimpleName());
	}
	
	private Resource initResource(URI uri) {
		ResourceSet rSet = new ResourceSetImpl();
		return rSet.createResource(uri);
	}
	
	protected void saveResource(Resource res) {
		try {
			res.save(null);
		} catch (IOException e) {
			Assertions.fail();
		}
	}
	
	public boolean getDefaultCheckStatementPosition() {
		return this.defaultCheckStatementPosition;
	}
	
	/**
	 * Prepares loggers. Enabling too many loggers can cause Java memory issues.
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
	
	protected DummySimilarityChecker initSC() {
        var builder = new DummySimilarityToolboxBuilder();
        builder.setSimilarityToolboxFactory(new MapSimilarityToolboxFactory());
        builder.setSwitchFactory(this.initSwitchFactory());
        
        var toolbox = builder.instantiate()
        	.buildNewSimilaritySwitchHandler()
        	.buildNormalizationHandlers()
        	.buildComparisonHandlers()
        	.build();
		
		this.sc = new DummySimilarityChecker(toolbox);
		return this.sc;
	}
	
	public String getExtension() {
		return this.extension;
	}
	
	public static String getAbstractSimilarityTestResourceRootPath() {
		return resourceRootPath;
	}
	
	public String getResourceRootPath() {
		return resourceRootPath;
	}
	
	public URI createURI(String resourceName, String resourceExtension) {
		return URI.createFileURI(this.getResourceRootPath()
				+ File.separator + resourceName + "." + resourceExtension);
	}
	
	public URI createURI(String resourceName) {
		return this.createURI(resourceName, this.getExtension());
	}
	
	public String getResourceName() {
		var count = 1;
		
		var prefix = this.getResourceFileTestPrefix() +
				"_" + this.getResourceFileTestIdentifier();
		
		var resourceRoot = new File(this.getResourceRootPath());
		
		if (resourceRoot.exists()) {
			count = resourceRoot.listFiles((f) -> f.getName().equals(prefix)).length + count;
		}
		
		return prefix + "_" + count;
	}
	
	/**
	 * Creates a {@link Resource} instance for the given EObject instances.
	 * <br><br>
	 * <b>!!! IMPORTANT !!!</b>
	 * <br><br>
	 * <b>Using this method will cause {@link AbstractSimilarityTest#LOGGER} to send
	 * an error message, if some of the EObject instances (from eos) that are already in
	 * a Resource instance are attempted to be placed into another Resource. This
	 * should be avoided, since doing so will REMOVE the said EObject instances
	 * from their former Resource and cause side effects in tests.</b>
	 */
	protected Resource createResource(Collection<? extends EObject> eos) {
		Resource res = this.initResource(this.createURI(this.getResourceName()));
		this.createdResources.add(res);
		
		if (eos != null) {
			for (var eo : eos) {
				
				/*
				 * Make sure to not add an EObject, which has already been added to a Resource, to another Resource.
				 * Doing so will detach it from its former Resource and add it to the second one.
				 */
				if (eo.eResource() != null) {
					this.getLogger().error("An EObject's resource was set and shifted during resource creation");
				}
				res.getContents().add(eo);
			}
		}
		
		return res;
	}
	
	public void setResourceRegistry(String extension) {
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(extension, new XMIResourceFactoryImpl());
	}
	
	public void unloadResource(Resource res) {
		res.unload();
	}
	
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
	
	public void deleteResourceDir() {
		new File(this.getResourceRootPath()).delete();
	}
	
	public void cleanRegistry() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		reg.getExtensionToFactoryMap().remove(this.getExtension());
	}
	
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.sc.isSimilar(element1, element2);
	}
	
	@SuppressWarnings("unchecked")
	public Boolean areSimilar(Collection<? extends EObject> elements1, Collection<? extends EObject> elements2) {
		return this.sc.areSimilar((Collection<Object>) elements1, (Collection<Object>) elements2);
	}
	
	/**
	 * Override in tests, if the underlying similarity switch
	 * needs to have only the specified switches.
	 */
	public InnerSwitchFactory initSwitchFactory() {
		return null;
	}
	
	public String getResourceFileTestPrefix() {
		return this.testPrefix;
	}
	
	public String getResourceFileTestIdentifier() {
		return this.testIdentifier;
	}
	
	protected void setResourceFileTestPrefix(String testPrefix) {
		this.testPrefix = testPrefix;
	}
	
	protected void setResourceFileTestIdentifier(String testIdentifier) {
		this.testIdentifier = testIdentifier;
	}
}
