package cipm.consistency.vsum.test.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cipm.consistency.commitintegration.CommitIntegrationState;
import cipm.consistency.commitintegration.git.GitRepositoryWrapper;
import cipm.consistency.commitintegration.lang.java.JavaModelFacade;
import cipm.consistency.commitintegration.settings.CommitIntegrationSettingsContainer;
import cipm.consistency.vsum.test.appspace.LoggingSetup;
import cipm.consistency.vsum.test.java.ApacheCommonsRepoEntries.RepoEntry;
import jamopp.resource.JavaResource2Factory;
import cipm.consistency.base.models.instrumentation.InstrumentationModel.InstrumentationModelPackage;

/*
 * TODO Fix
 * tools.vitruv.applications.util.temporary.JavaTypeUtil.hasSameTargetReference(
 * TypeReference, TypeReference)
 * 
 * Currently, there are 2 scenarios that lead to NullPointerExceptions:
 * 
 * 1) reference1 == null ^ reference2 == null
 * 
 * 2) target1 == null ^ target2 == null
 * 
 * In the case of 1) or 2), the method should return false. The fix is not
 * included, since the Vitruvius packages come from a GIT submodule. Hence it
 * currently has to be fixed manually by editing JavaTypeUtil.xtend and then
 * re-generating the corresponding .java files.
 * 
 * After fixing the above-mentioned bug, the change propagation of the Apache
 * repositories at the given commits runs successfully. Note that this is a
 * commit integration test case, meaning that the models (Java, PCM, IM) will be
 * build from scratch.
 */
public class ApacheCommonsTestController {
	private static final Logger LOGGER = Logger.getLogger(ApacheCommonsTestController.class);
	private CommitIntegrationState<JavaModelFacade> state;
	private ApacheCommonsCommitIntegration apacheCommonsController;

	private Path localRepositoriesDir = Paths.get("target", "apache-commons");

	private Path rootPath = Paths.get("target", "ApacheCommonsTest");

	private void initContainerRepo(GitRepositoryWrapper wrapper) throws GitAPIException, IOException {
		if (Files.exists(this.localRepositoriesDir)) {
			// Initialize the repositories within this directory.
			LOGGER.debug("Initialising a pre-existing container repository");
			wrapper.withLocalDirectory(this.localRepositoriesDir).initialize();
			LOGGER.debug("Initialised a pre-existing container repository");
		} else {
			// Initialize the container repository
			LOGGER.debug("Initialising a new container repository");
			wrapper.initNewRepository(this.localRepositoriesDir.toFile());
			LOGGER.debug("Initialised the new container repository");
		}
	}

	private void ensureSubmoduleConfiguration(GitRepositoryWrapper wrapper, Map<String, RepoEntry> repoMap)
			throws GitAPIException, IOException {
		boolean submodulesChanged = false;

		for (var e : repoMap.entrySet()) {
			var relativeSubmodulePath = e.getKey();
			var commitId = e.getValue().commitId;

			// Add the submodule, if it is not registered yet
			//
			// "git submodule add"
			if (!wrapper.isSubmoduleRegistered(relativeSubmodulePath)) {
				LOGGER.debug(relativeSubmodulePath + " is missing as a submodule");
				LOGGER.debug("Adding " + relativeSubmodulePath + " as a submodule");
				wrapper.addSubmodule(e.getValue().remoteRepoURI, relativeSubmodulePath);
				LOGGER.debug("Added " + relativeSubmodulePath + " as a submodule");

//				LOGGER.debug("Committing " + relativeSubmodulePath + " being added as a submodule");
//				wrapper.commitAllSubmoduleChanges("Added submodule: " + relativeSubmodulePath);
//				LOGGER.debug("Committed " + relativeSubmodulePath + " being added as a submodule");

				submodulesChanged = true;
			} else {
				LOGGER.debug(relativeSubmodulePath + " is already registered");
			}

			// Initialise and clone the submodule, if it does not physically exist
			//
			// "git submodule init submodule_name"
			// "git submodule update submodule_name"
			if (!wrapper.isSubmodulePhysicallyPresent(relativeSubmodulePath)) {
				LOGGER.debug(relativeSubmodulePath + " is not physically present");
				LOGGER.debug("Initialising and cloning " + e.getKey());
				wrapper.initAndCloneSubmodule(relativeSubmodulePath);
				LOGGER.debug("Initialised and cloned " + e.getKey());

				// Note: Initialising and cloning the contents of a submodule does not result in
				// any changes to the container repository
			} else {
				LOGGER.debug(relativeSubmodulePath + " is already physically present");
			}

			// Checkout submodule to the given commitId, if the submodule is not already
			// checked out there
			if (!wrapper.isSubmoduleCheckedOutAt(relativeSubmodulePath, commitId)) {
				LOGGER.debug(relativeSubmodulePath + " is not checked out at " + commitId);
				LOGGER.debug("Checking out " + relativeSubmodulePath + " at " + commitId);
				wrapper.checkoutInSubmodule(relativeSubmodulePath, commitId);
				LOGGER.debug("Checked out " + relativeSubmodulePath + " at " + commitId);

				submodulesChanged = true;
			} else {
				LOGGER.debug(relativeSubmodulePath + " is already checked out at " + commitId);
			}

//			LOGGER.debug("Committing " + e.getKey() + " being checked out at " + e.getValue().commitId);
//			wrapper.commitSubmoduleChange(relativeSubmodulePath, "Checked out submodule");
//			LOGGER.debug("Committed " + e.getKey() + " being checked out at " + e.getValue().commitId);
		}

		// TODO Check for submodules that should be removed and deal with them

		// Commit all submodule changes at once, in order to keep the commit history of
		// the parent repository clean and to make sure that each test case has exactly
		// one commit. Only commit, if the submodules changed at all
		if (submodulesChanged) {
			// TODO Add more information about the test case to the commit message, so that
			// which tests run and what happens is clear

			LOGGER.debug("Committing all submodule changes");
			wrapper.commitAllSubmoduleChanges("Set submodule configuration");
			LOGGER.debug("Committed all submodule changes");
		} else {
			LOGGER.debug("No submodule changes to commit");
		}
	}

	/**
	 * 
	 * @param overwrite Are existing files (models, etc.) to be deleted before
	 *                  initializing the commit integration state?
	 * @throws GitAPIException
	 * @throws IOException
	 * @throws org.eclipse.jgit.api.errors.TransportException
	 * @throws InvalidRemoteException
	 */
	protected void setup(boolean overwrite, Map<String, RepoEntry> repoMap) {
		// Create new empty state
		this.apacheCommonsController = new ApacheCommonsCommitIntegration(this.rootPath);

		// overwrite existing files?
		try {
			CommitIntegrationSettingsContainer
					.initialize(Paths.get("apache-commons-exec-files", "settings.properties"));
			this.apacheCommonsController.initialize(this.apacheCommonsController);
			this.state = this.apacheCommonsController.getState();
			var wrapper = this.state.getGitRepositoryWrapper();
			// state.initialize(this.teammatesController,
			// this.teammatesController.getRootPath(), overwrite);

			initContainerRepo(wrapper);
			ensureSubmoduleConfiguration(wrapper, repoMap);
		} catch (IOException | GitAPIException e) {
			e.printStackTrace();
			failTest("Unable to setup commit integration state");
		}
	}

	@BeforeEach
	public void setup() {
		LoggingSetup.setMinLogLevel(Level.DEBUG);
	}

	@BeforeAll
	public static void setupStatic() {
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("java", new JavaResource2Factory());
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("javaxmi", new JavaResource2Factory());
		InstrumentationModelPackage.eINSTANCE.eClass();
	}

	@AfterEach
	public void cleanupAfterTest() {
		state.dispose();
	}

	protected void failTest(String msg) {
		LOGGER.error(msg);
		Assert.fail(msg);
	}

	@Test
	public void testApacheCommonsIntegration() {
		// TODO Run both integration and propagation test cases

		setup(false, ApacheCommonsRepoEntries.getCaseVitruvTestCase());

		var result = this.apacheCommonsController.propagateCurrentCheckout();
		System.out.println(result.get());
	}

	@Test
	public void testApacheCommonsPropagation() {
		setup(false, ApacheCommonsRepoEntries.getMinimalPropagationTestCase());

		var result = this.apacheCommonsController.propagateCurrentCheckout();
		System.out.println(result.get());
	}
}
