package cipm.consistency.fitests.similarity.java;

import java.io.File;
import java.util.Collection;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
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
	 * @return The absolute path, under which the {@link Resource} files will be
	 * saved.
	 */
	public static String getAbsoluteResourceRootPath() {
		return new File("").getAbsoluteFile().getAbsolutePath() + File.separator
				+ "testModels";
	}
	
	/**
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public static String getResourceFileExtension() {
		return "javaxmi";
	}
	
	private ResourceHelper resHelper;
	
	/**
	 * The similarity checker that will handle similarity checking operations.
	 */
	private ISimilarityChecker sc;

	/**
	 * An object that contains information on the currently running test.
	 */
	private TestInfo currentTestInfo;
	
	/**
	 * Sets up the necessary variables before tests are run. The
	 * {@link TestInfo} parameter is included, so that test-specific
	 * set up can be performed.
	 * 
	 * @param info An object that contains information about the current
	 * test to be run (ex: the test method instance, test class, ...)
	 */
	@BeforeEach
	public void setUp(TestInfo info) {
		this.currentTestInfo = info;

		this.setUpLogger();
		this.setUpResourceHelper();
		
		this.setSimilarityChecker(this.initSC());
	}

	@AfterEach
	public void tearDown() {
		this.getResourceHelper().clean();

		this.resetAfterTest();
	}
	
	/**
	 * Sets up the {@link ResourceHelper} instance that will be used.
	 */
	protected void setUpResourceHelper() {
		this.resHelper = new ResourceHelper();

		this.getResourceHelper().setResourceRootPath(getAbsoluteResourceRootPath());
		this.getResourceHelper().setResourceFileExtension(getResourceFileExtension());
	}
	
	private ResourceHelper getResourceHelper() {
		return this.resHelper;
	}
	
	/**
	 * @param info An object that contains information on a
	 * test.
	 * 
	 * @return The name of the test method, to whom the info
	 * parameter belongs. Returns an empty String, if info is
	 * null or info does not contain a test method.
	 */
	protected String getCurrentTestMethodName(TestInfo info) {
		if (info != null) {
			var met = info.getTestMethod().orElseGet(() -> null);
			
			if (met != null) {
				return met.getName();
			}
		}

		return "";
	}

	/**
	 * Resets all stored attributes related to individual tests.
	 */
	protected void resetAfterTest() {
		this.sc = null;
		this.currentTestInfo = null;
	}

	/**
	 * @return The logger of the current test class.
	 */
	protected Logger getLogger() {
		return Logger.getLogger("cipm." + this.getClass().getSimpleName());
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
		
		// Enable to receive log messages from similarity switches
//		logger = Logger.getLogger("javaswitch");
//		logger.setLevel(Level.ALL);
		
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
	 * @see {@link #getResourceFileName()}
	 */
	public String getCurrentTestClassName() {
		return this.getClass().getSimpleName();
	}

	/**
	 * @return The name of the currently running test method
	 */
	public String getCurrentTestMethodName() {
		return this.getCurrentTestMethodName(this.currentTestInfo);
	}
	
	/**
	 * Delegates the creation of a {@link Resource} instance to the underlying
	 * {@link ResourceHelper}.
	 * <br><br>
	 * The name of the {@link Resource} instance will be the return value of
	 * {@link #getResourceFileName()}.
	 * 
	 * @return A {@link Resource} instance with the given contents
	 */
	protected Resource createResource(Collection<? extends EObject> eos) {
		return this.getResourceHelper().createResource(eos, this.getResourceFileName());
	}
	
	/**
	 * Uses the currently run test class and method to compute a name for the
	 * file of the {@link Resource} instance, should it be saved.
	 * 
	 * @return A name for the file of the {@link Resource} instance.
	 */
	public String getResourceFileName() {
		return this.getCurrentTestClassName() + "_" + this.getCurrentTestMethodName(this.currentTestInfo);
	}
}
