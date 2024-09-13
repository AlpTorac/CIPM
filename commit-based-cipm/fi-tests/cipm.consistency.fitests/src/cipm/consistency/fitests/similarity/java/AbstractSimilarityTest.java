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

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.SimilarityCheckerContainer;
import cipm.consistency.fitests.similarity.eobject.EObjectSimilarityTest;
import cipm.consistency.fitests.similarity.eobject.ResourceHelper;
import cipm.consistency.fitests.similarity.eobject.java.JavaSimilarityCheckerProvider;

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
	 *         saved.
	 */
	public static String getAbsoluteResourceRootPath() {
		return new File("").getAbsoluteFile().getAbsolutePath() + File.separator + "testModels";
	}

	/**
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public static String getResourceFileExtension() {
		return "javaxmi";
	}

	private ResourceHelper resHelper;

	/**
	 * The {@link ISimilarityCheckerContainer} that will be used to store the
	 * {@link ISimilarityChecker} under test.
	 */
	private ISimilarityCheckerContainer scc;

	/**
	 * An object that contains information on the currently running test.
	 */
	private TestInfo currentTestInfo;

	/**
	 * Sets up the necessary variables before tests are run. The {@link TestInfo}
	 * parameter is included, so that test-specific set up can be performed.
	 * 
	 * @param info An object that contains information about the current test to be
	 *             run (ex: the test method instance, test class, ...)
	 */
	@BeforeEach
	public void setUp(TestInfo info) {
		this.currentTestInfo = info;

		this.setUpLogger();
		this.setUpResourceHelper();

		this.setSimilarityCheckerContainer(this.initSCC());
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

		this.getResourceHelper().setResourceSaveRootPath(getAbsoluteResourceRootPath());
		this.getResourceHelper().setResourceFileExtension(getResourceFileExtension());
	}

	private ResourceHelper getResourceHelper() {
		return this.resHelper;
	}

	/**
	 * @param info An object that contains information on a test.
	 * 
	 * @return The name of the test method, to whom the info parameter belongs.
	 *         Returns an empty String, if info is null or info does not contain a
	 *         test method.
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
		this.scc = null;
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
	 * Creates the concrete {@link ISimilarityCheckerContainer} that will be used to
	 * store the {@link ISimilarityChecker} under test. <br>
	 * <br>
	 * If necessary, it can be overridden in tests to change the said similarity
	 * checker during set up.
	 */
	protected ISimilarityCheckerContainer initSCC() {
		var scc = new SimilarityCheckerContainer();
		scc.setSimilarityCheckerProvider(new JavaSimilarityCheckerProvider());
		return scc;
	}

	/**
	 * Sets the used {@link ISimilarityCheckerContainer} to the given one. <br>
	 * <br>
	 * If necessary, it can be called in tests to change the used similarity checker
	 * container to the given one.
	 * 
	 * @see {@link #initSCC()} for setting the {@link ISimilarityCheckerContainer}
	 *      during set up.
	 */
	protected void setSimilarityCheckerContainer(ISimilarityCheckerContainer scc) {
		this.scc = scc;
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.scc.isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	@SuppressWarnings("unchecked")
	public Boolean areSimilar(Collection<? extends EObject> elements1, Collection<? extends EObject> elements2) {
		return this.scc.areSimilar((Collection<Object>) elements1, (Collection<Object>) elements2);
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
	 * {@link ResourceHelper}. <br>
	 * <br>
	 * The name of the {@link Resource} instance will be the return value of
	 * {@link #getResourceFileName()}.
	 * 
	 * @return A {@link Resource} instance with the given contents
	 */
	protected Resource createResource(Collection<? extends EObject> eos) {
		return this.getResourceHelper().createResource(eos, this.getResourceFileName());
	}

	/**
	 * Uses the currently run test class and method to compute a name for the file
	 * of the {@link Resource} instance, should it be saved.
	 * 
	 * @return A name for the file of the {@link Resource} instance.
	 */
	public String getResourceFileName() {
		return this.getCurrentTestClassName() + "_" + this.getCurrentTestMethodName(this.currentTestInfo);
	}
}
