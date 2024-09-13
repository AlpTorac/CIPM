package cipm.consistency.fitests.similarity;

import java.util.Collection;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class AbstractSimilarityTest {
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

		this.setSimilarityCheckerContainer(this.initSCC());
	}

	/**
	 * Cleans up the variables set up with {@link #setUp(TestInfo)} and performs
	 * other necessary clean up operations.
	 */
	@AfterEach
	public void tearDown() {
		this.resetAfterTest();
	}

	/**
	 * @return An object that contains information on the currently running test.
	 */
	protected TestInfo getCurrentTestInfo() {
		return this.currentTestInfo;
	}

	/**
	 * @param info An object that contains information on a test.
	 * 
	 * @return The name of the test method, to whom the info parameter belongs.
	 *         Returns an empty String, if info is null or info does not contain a
	 *         test method.
	 */
	private String getCurrentTestMethodName(TestInfo info) {
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
		// logger = Logger.getLogger("javaswitch");
		// logger.setLevel(Level.ALL);

		// logger = Logger.getLogger("jamopp");
		// logger.setLevel(Level.ALL);
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
	protected abstract ISimilarityCheckerContainer initSCC();

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
	 * Delegates similarity checking to the underlying
	 * {@link ISimilarityCheckerContainer}.
	 */
	public Boolean isSimilar(Object element1, Object element2) {
		return this.scc.isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying
	 * {@link ISimilarityCheckerContainer}.
	 */
	public Boolean areSimilar(Collection<?> elements1, Collection<?> elements2) {
		return this.scc.areSimilar(elements1, elements2);
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
		return this.getCurrentTestMethodName(this.getCurrentTestInfo());
	}

}