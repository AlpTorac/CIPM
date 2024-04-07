package cipm.consistency.fitests.similarity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class AbstractSimilarityTest {
	private static final Logger LOGGER = Logger.getLogger("cipm." + AbstractSimilarityTest.class.getSimpleName());
	
	private final String resourceRootPath = new File("").getAbsoluteFile().getAbsolutePath()
			+ File.separator + "testModels";
	
	private final String extension = "javaxmi";
	
	private final List<Resource> createdResources = new ArrayList<Resource>();
	
	private DummySimilarityChecker sc;
	
	@BeforeEach
	public void setUp() {
		this.setUpLogger();
		LOGGER.debug("Resource root path set to: " + this.getResourceRootPath());
		this.setResourceRegistry(this.getResourceRootPath());
		this.sc = this.initSC();
	}
	
	@AfterEach
	public void tearDown() {
		this.cleanAllResources();
		this.cleanRegistry();
		this.deleteResourceDir();
	}
	
	/**
	 * Prepares loggers. Enabling too many loggers can cause Java memory issues.
	 */
	protected void setUpLogger() {
		Logger logger = Logger.getLogger("cipm");
		logger.setLevel(Level.ALL);
		logger = Logger.getLogger("jamopp");
		logger.setLevel(Level.ALL);
		logger = Logger.getRootLogger();
		logger.removeAllAppenders();
		ConsoleAppender ap = new ConsoleAppender(new PatternLayout("[%d{DATE}] %-5p: %c - %m%n"),
				ConsoleAppender.SYSTEM_OUT);
		logger.addAppender(ap);
	}
	
	protected DummySimilarityChecker initSC() {
		this.sc = new DummySimilarityChecker();
		return this.sc;
	}
	
	protected void setSwitchFactory(InnerSwitchFactory switchFac) {
		this.sc.setSwitchFactory(switchFac);
	}
	
	public String getExtension() {
		return this.extension;
	}
	
	public String getResourceRootPath() {
		return this.resourceRootPath;
	}
	
	public URI createURI(String resourceName, String resourceExtension) {
		return URI.createFileURI(this.getResourceRootPath()
				+ File.separator + resourceName + "." + resourceExtension);
	}
	
	public URI createURI(String resourceName) {
		return this.createURI(resourceName, this.getExtension());
	}
	
	public Resource createResource(String resourceName,
			IJavaModelConstructor ctor, Map<String, Object> params) {
		Resource res = null;
		
		try {
			res = ctor.createResource(this.createURI(resourceName), params);
			this.createdResources.add(res);
		} catch (IOException e) {
			e.printStackTrace();
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
			try {
				this.unloadResource(r);
				r.delete(null);
			} catch (IOException e) {
				e.printStackTrace();
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
	
	public Boolean areSimilar(final List<? extends EObject> elements1, final List<? extends EObject> elements2) {
		return this.sc.areSimilar(elements1, elements2);
	}
	
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.sc.isSimilar(element1, element2);
	}
	
	public Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {
		return this.sc.isSimilar(element1, element2, checkStatementPosition);
	}
}
