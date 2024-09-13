package cipm.consistency.fitests.similarity.java;

import java.io.File;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;
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
public abstract class AbstractJavaSimilarityTest extends AbstractSimilarityTest {
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
	 * Sets up the necessary variables before tests are run. The {@link TestInfo}
	 * parameter is included, so that test-specific set up can be performed.
	 * 
	 * @param info An object that contains information about the current test to be
	 *             run (ex: the test method instance, test class, ...)
	 */
	@BeforeEach
	public void setUp(TestInfo info) {
		super.setUp(info);
		this.setUpResourceHelper();
	}

	@AfterEach
	public void tearDown() {
		this.getResourceHelper().clean();
		this.resHelper = null;

		super.resetAfterTest();
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
	 * Sets up the {@link ResourceHelper} instance that will be used.
	 */
	protected void setUpResourceHelper() {
		this.resHelper = new ResourceHelper();

		this.getResourceHelper().setResourceSaveRootPath(getAbsoluteResourceRootPath());
		this.getResourceHelper().setResourceFileExtension(getResourceFileExtension());
	}

	protected ResourceHelper getResourceHelper() {
		return this.resHelper;
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
		return this.getCurrentTestClassName() + "_" + this.getCurrentTestMethodName();
	}
}
