package cipm.consistency.fitests.similarity.eobject;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;

/**
 * An abstract class that extends {@link AbstractSimilarityTest} with additional
 * methods regarding {@link Resource} instance creation.
 * 
 * @author atora
 */
public abstract class AbstractResourceSimilarityTest extends AbstractSimilarityTest {
	/**
	 * The {@link ResourceHelper} instance that can be used for creating
	 * {@link Resource} instances.
	 */
	private ResourceHelper resHelper;

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);
		this.setUpResourceHelper();
	}

	@AfterEach
	@Override
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

	/**
	 * The {@link ResourceHelper} instance that can be used for creating
	 * {@link Resource} instances.
	 */
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
