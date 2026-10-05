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
import cipm.consistency.commitintegration.lang.java.JavaModelFacade;
import cipm.consistency.commitintegration.settings.CommitIntegrationSettingsContainer;
import cipm.consistency.vsum.test.appspace.LoggingSetup;
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

	private Map<String, RepoEntry> integrationRepoIdToEntry = new HashMap<>() {
		{

			// "rel/commons-csv-1.14.1"
			put("commons-csv", new RepoEntry("commons-csv", "https://github.com/apache/commons-csv", "e14ef8"));

			// "rel/commons-exec-1.6.0"
			put("commons-exec", new RepoEntry("commons-exec", "https://github.com/apache/commons-exec", "3ee697"));

			// "commons-cli-1.11.0-RC1 rel/commons-cli-1.11.0"
			put("commons-cli", new RepoEntry("commons-cli", "https://github.com/apache/commons-cli", "d74613"));

			// "commons-statistics-1.3-RC1 rel/commons-statistics-1.3"
			put("commons-statistics",
					new RepoEntry("commons-statistics", "https://github.com/apache/commons-statistics", "2937eb"));
		}
	};

	private Map<String, RepoEntry> propagationRepoIdToEntry = new HashMap<>() {
		{
			// "commons-csv-1.14.0-RC1 rel/commons-csv-1.14.0"
			put("commons-csv", new RepoEntry("commons-csv", "https://github.com/apache/commons-csv", "969d42"));

			// "commons-exec-1.5.0-RC1 rel/commons-exec-1.5.0"
			put("commons-exec", new RepoEntry("commons-exec", "https://github.com/apache/commons-exec", "59c60c"));

			// "commons-cli-1.10.0-RC1 rel/commons-cli-1.10.0"
			put("commons-cli", new RepoEntry("commons-cli", "https://github.com/apache/commons-cli", "045811"));

			// No tag
			put("commons-statistics",
					new RepoEntry("commons-statistics", "https://github.com/apache/commons-statistics", "9334ef"));
		}
	};

	private Path rootPath = Paths.get("target", "ApacheCommonsTest");

	private static class RepoEntry {
		public final String repoId;
		public final String remoteRepoURI;
		public final String commitId;

		private RepoEntry(String repoId, String remoteRepoURI, String commitId) {
			this.repoId = repoId;
			this.remoteRepoURI = remoteRepoURI;
			this.commitId = commitId;
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
			if (Files.exists(this.localRepositoriesDir)) {
				// Initialize the repositories within this directory.
				LOGGER.debug("Initialising a pre-existing container repository");
				wrapper.withLocalDirectory(this.localRepositoriesDir.resolve(".git")).initialize();

				var checkedOutAtCorrectCommits = true;

				// Ensure that the submodules are checked out at the correct commits
				LOGGER.debug("Checking submodule HEAD locations");
				for (var e : repoMap.entrySet()) {
					if (!wrapper.isSubmoduleCheckedOutAt(e.getKey(), e.getValue().commitId)) {
						LOGGER.debug("Mismatching HEAD location detected in submodule " + e.getKey());
						checkedOutAtCorrectCommits = false;
						LOGGER.debug("Checking out " + e.getKey() + " at " + e.getValue().commitId);
						wrapper.checkoutInSubmodule(e.getKey(), e.getValue().commitId);
					}
				}

				// Commit the desired HEAD configuration in the container repository
				if (!checkedOutAtCorrectCommits) {
					LOGGER.debug("Committing the desired submodule HEAD locations");
					wrapper.commitAllSubmoduleChanges("Adjusted submodule HEAD locations");
				}
			} else {
				// Initialize the container repository
				LOGGER.debug("Initialising a container repository");
				wrapper.initNewRepository(this.localRepositoriesDir.toFile());

				// Setup each submodule
				for (var e : repoMap.entrySet()) {
					var relativeSubmodulePath = e.getKey();
					LOGGER.debug("Adding " + e.getKey() + " as a submodule");
					wrapper.addSubmodule(e.getValue().remoteRepoURI, relativeSubmodulePath);
					LOGGER.debug("Committing " + e.getKey() + " being added as a submodule");
					wrapper.commitAllSubmoduleChanges("Added submodules");
					LOGGER.debug("Committed " + e.getKey() + " being added as a submodule");
					LOGGER.debug("Initialising and cloning " + e.getKey());
					wrapper.initAndCloneSubmodule(relativeSubmodulePath);
					LOGGER.debug("Initialised and cloned " + e.getKey());
					LOGGER.debug("Checking out " + e.getKey());
					wrapper.checkoutInSubmodule(relativeSubmodulePath, e.getValue().commitId);
					LOGGER.debug("Checked out " + e.getKey());
					LOGGER.debug("Committing " + e.getKey() + " being checked out at " + e.getValue().commitId);
					wrapper.commitSubmoduleChange(relativeSubmodulePath, "Checked out submodule");
					LOGGER.debug("Committed " + e.getKey() + " being checked out at " + e.getValue().commitId);
				}

				LOGGER.debug("Initialised the container repository");
			}
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
		setup(false, integrationRepoIdToEntry);

		var result = this.apacheCommonsController.propagateCurrentCheckout();
		System.out.println(result.get());
	}

	@Test
	public void testApacheCommonsPropagation() {
		setup(false, propagationRepoIdToEntry);

		var result = this.apacheCommonsController.propagateCurrentCheckout();
		System.out.println(result.get());
	}
}
