package cipm.consistency.fitests.similarity.eobject;

import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;

/**
 * An abstract class meant to be implemented by EObject-related test classes.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractEObjectSimilarityTest extends AbstractSimilarityTest {
	/**
	 * @see {@link #getResourceHelper()}
	 */
	private AbstractResourceHelper resHelper;

	/**
	 * @see {@link #getResourceParsingStrategy()}
	 */
	private AbstractResourceParsingStrategy parsingStrat;

	/**
	 * @see {@link #getResourceTestOptions()}
	 */
	private ResourceTestOptions resourceTestOptions;

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		this.setResourceHelper(this.initResourceHelper());
		this.getResourceHelper().setResourceSaveRootPath(this.getAbsoluteResourceRootPath());

		this.setResourceParsingStrategy(this.initResourceParsingStrategy());
		this.setResourceTestOptions(this.initResourceTestOptions());
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Sets {@link #getResourceHelper()}, {@link #getResourceParsingStrategy()} and
	 * {@link #getResourceTestOptions()} to null; in order to ensure that each test
	 * has freshly created instances.
	 */
	@AfterEach
	@Override
	public void tearDown() {
		this.cleanUpResourceHelper();
		this.cleanUpResourceParsingStrategy();
		this.cleanUpResourceTestOptions();

		super.tearDown();
	}

	/**
	 * The {@link DefaultResourceHelper} instance that can be used for creating
	 * {@link Resource} instances.
	 */
	protected AbstractResourceHelper getResourceHelper() {
		return this.resHelper;
	}

	/**
	 * Sets up the {@link DefaultResourceHelper} instance that will be used with the
	 * given one.
	 */
	protected void setResourceHelper(AbstractResourceHelper resHelper) {
		this.resHelper = resHelper;
	}

	protected AbstractResourceParsingStrategy getResourceParsingStrategy() {
		return this.parsingStrat;
	}

	protected void setResourceParsingStrategy(AbstractResourceParsingStrategy parsingStrat) {
		this.parsingStrat = parsingStrat;
	}

	/**
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public String getResourceFileExtension() {
		return this.getResourceHelper().getResourceFileExtension();
	}

	protected ResourceTestOptions getResourceTestOptions() {
		return this.resourceTestOptions;
	}

	protected void setResourceTestOptions(ResourceTestOptions resourceTestOptions) {
		this.resourceTestOptions = resourceTestOptions;
	}

	protected void cleanUpResourceHelper() {
		this.resHelper = null;
	}

	protected void cleanUpResourceParsingStrategy() {
		this.parsingStrat = null;
	}

	protected void cleanUpResourceTestOptions() {
		this.resourceTestOptions = null;
	}

	/**
	 * Override in implementors to change the default value, if needed.
	 * 
	 * @return The {@link AbstractResourceHelper} that will be initially used.
	 */
	protected abstract AbstractResourceHelper initResourceHelper();

	protected abstract ResourceTestOptions initResourceTestOptions();

	protected abstract AbstractResourceParsingStrategy initResourceParsingStrategy();

	/**
	 * @return The absolute path, under which the {@link Resource} files will be
	 *         saved.
	 */
	public abstract Path getAbsoluteResourceRootPath();
}
