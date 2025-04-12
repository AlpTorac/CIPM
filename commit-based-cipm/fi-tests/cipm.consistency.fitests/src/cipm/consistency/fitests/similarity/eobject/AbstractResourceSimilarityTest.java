package cipm.consistency.fitests.similarity.eobject;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import cipm.consistency.fitests.similarity.AbstractSimilarityTest;

/**
 * An abstract class that extends {@link AbstractSimilarityTest} with additional
 * methods regarding {@link Resource} instance creation.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractResourceSimilarityTest extends AbstractSimilarityTest {
	/**
	 * @see {@link #getResourceHelper()}
	 */
	private AbstractResourceHelper resHelper;

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(info);

		this.setResourceHelper(this.getInitialResourceHelper());
		this.getResourceHelper().setResourceSaveRootPath(this.getAbsoluteResourceRootPath());
	}

	@AfterEach
	@Override
	public void tearDown() {
		this.cleanUpResourceHelper();

		super.tearDown();
	}

	/**
	 * Override in implementors to change the default value, if needed.
	 * 
	 * @return The {@link AbstractResourceHelper} that will be initially used.
	 */
	protected AbstractResourceHelper getInitialResourceHelper() {
		return new ResourceHelper();
	}

	/**
	 * Cleans and sets the used {@link ResourceHelper} to null, in order to ensure
	 * that each test method has a fresh instance.
	 */
	protected void cleanUpResourceHelper() {
		if (this.shouldCleanResourceRegistry()) {
			this.getResourceHelper().cleanRegistry();
		}

		if (this.shouldDeleteAllResources()) {
			this.getResourceHelper().cleanAllResources();
		} else if (this.shouldUnloadAllResources()) {
			this.getResourceHelper().unloadAllResources();
		}

		this.resHelper = null;
	}

	/**
	 * Sets up the {@link ResourceHelper} instance that will be used with the given
	 * one.
	 */
	protected void setResourceHelper(AbstractResourceHelper resHelper) {
		this.resHelper = resHelper;
	}

	/**
	 * The {@link ResourceHelper} instance that can be used for creating
	 * {@link Resource} instances.
	 */
	protected AbstractResourceHelper getResourceHelper() {
		return this.resHelper;
	}

	/**
	 * Delegates the creation of a {@link Resource} instance to the underlying
	 * {@link AbstractResourceHelper}. <br>
	 * <br>
	 * The name of the {@link Resource} instance will be the return value of
	 * {@link #getResourceFileName()}.
	 * 
	 * @return A {@link Resource} instance with the given contents
	 * 
	 * @see {@link #getResourceFileName()}
	 */
	protected Resource createResource(Collection<? extends EObject> eos) {
		var resHelper = this.getResourceHelper();
		return resHelper.createResource(eos, resHelper.createResourceSet(), this.getResourceFileName());
	}

	/**
	 * Delegates the creation of a {@link Resource} instance to the underlying
	 * {@link AbstractResourceHelper}.
	 * 
	 * @return A {@link Resource} instance with the given contents
	 */
	protected Resource createResource(Collection<? extends EObject> eos, URI resURI) {
		var resHelper = this.getResourceHelper();
		return resHelper.createResource(eos, resHelper.createResourceSet(), resURI);
	}

	/**
	 * Delegates the creation of a {@link Resource} instance to the underlying
	 * {@link AbstractResourceHelper}.
	 * 
	 * @return An empty {@link Resource} instance
	 * 
	 * @see {@link #getResourceFileName()}
	 */
	protected Resource createResource() {
		var resHelper = this.getResourceHelper();
		return resHelper.createResource(null, resHelper.createResourceSet(), this.getResourceFileName());
	}

	/**
	 * Delegates the creation of a {@link Resource} instance to the underlying
	 * {@link AbstractResourceHelper}. <br>
	 * <br>
	 * Note: The given URI will override the path given by
	 * {@link #getAbsoluteResourceRootPath()}.
	 * 
	 * @return An empty {@link Resource} instance with the given URI.
	 */
	protected Resource createResource(URI resURI) {
		var resHelper = this.getResourceHelper();
		return resHelper.createResource(null, resHelper.createResourceSet(), resURI);
	}

	/**
	 * Delegates the creation of a {@link ResourceSet} instance to the underlying
	 * {@link AbstractResourceHelper}.
	 * 
	 * @return An empty {@link ResourceSet} instance
	 */
	protected ResourceSet createResourceSet() {
		return this.getResourceHelper().createResourceSet();
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
	 * @return The extension of the {@link Resource} files, if they are saved.
	 */
	public String getResourceFileExtension() {
		return this.getResourceHelper().getResourceFileExtension();
	}

	public void refreshResourceRegistry() {
		if (this.getResourceHelper().areRequiredResourceRegistriesPresent()) {
			this.getResourceHelper().setInitialResourceRegistries();
		}
	}

	/**
	 * @return Whether resource registry modifications should be undone after each
	 *         test. Override in implementors, if necessary.
	 */
	public boolean shouldCleanResourceRegistry() {
		return true;
	}

	/**
	 * Can be used to clean up memory, if the created resource files cause memory
	 * issues. Override in implementors, if necessary.
	 * 
	 * @return Whether all created resource instances should be unloaded after each
	 *         test. Defaults to true.
	 */
	public boolean shouldUnloadAllResources() {
		return true;
	}

	/**
	 * Can be used to remove all created resource files, if they are not needed.
	 * Override in implementors, if necessary.
	 * 
	 * @return Whether all created resource files should be deleted after each test.
	 *         Defaults to false.
	 */
	public boolean shouldDeleteAllResources() {
		return false;
	}

	/**
	 * @return The absolute path, under which the {@link Resource} files will be
	 *         saved.
	 */
	public abstract Path getAbsoluteResourceRootPath();
}
