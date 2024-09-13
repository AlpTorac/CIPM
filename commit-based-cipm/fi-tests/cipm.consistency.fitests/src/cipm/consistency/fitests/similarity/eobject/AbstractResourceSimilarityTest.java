package cipm.consistency.fitests.similarity.eobject;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;

public abstract class AbstractResourceSimilarityTest extends AbstractSimilarityTest {
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
	 * Sets up the {@link ResourceHelper} instance that will be used.
	 */
	protected void setUpResourceHelper() {
		this.resHelper = new ResourceHelper();

		this.getResourceHelper().setResourceSaveRootPath(getAbsoluteResourceRootPath());
		this.getResourceHelper().setResourceFileExtension(this.getResourceFileExtension());
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
	
	/**
	 * @return The absolute path, under which the {@link Resource} files will be
	 *         saved.
	 */
	public abstract String getAbsoluteResourceRootPath();

	/**
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public abstract String getResourceFileExtension();
}
