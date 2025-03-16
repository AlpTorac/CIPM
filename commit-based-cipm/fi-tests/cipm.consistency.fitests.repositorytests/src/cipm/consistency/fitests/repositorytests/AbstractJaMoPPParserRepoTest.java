package cipm.consistency.fitests.repositorytests;

import cipm.consistency.commitintegration.GitRepositoryWrapper;
import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;

import java.nio.file.Path;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractJaMoPPParserRepoTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final String repoModelImplDirName = "repo-testmodels";
	/**
	 * The name of the directory, where the main local repository clone will reside
	 */
	private static final String copyDirName = "Copy";

	/**
	 * Prepares local repository clones for all commits relevant for this test. Must
	 * be executed before all tests. <br>
	 * <br>
	 * Does nothing, if the desired repository clones are already there.
	 * 
	 * @see {@link #getCommitIDs()}
	 */
	protected void prepareLocalRepoClones() {
		var rootDir = this.getRootDirPath().toFile();
		if (!rootDir.exists() || rootDir.list().length == 0) {
			this.getLogger().debug(String.format("Repository clones do not exist under %s ... preparing them now",
					this.getRootDirPath().toString()));
			var wrapper = this.cloneRemoteRepo();
			this.prepareReposForCommits(this.getCommitIDs(), wrapper);
			this.closeGitWrapper(wrapper);
			var mainLocalClonePath = wrapper.getRootDirectory().toPath();
			this.getLogger().debug(
					String.format("Cleaning main local repository clone under: %s", mainLocalClonePath.toString()));
			this.cleanModels(mainLocalClonePath);
			this.getLogger().debug("Cleaned main local repository clone");
			this.getLogger().debug("Repository clones have been successfully prepared");
		}
		this.getLogger().debug("Repository clones are ready");
	}

	/**
	 * Clones the remote repository desired by this test.
	 * 
	 * @return An object that can be used to perform GIT operations on the
	 *         repository clone.
	 */
	protected GitRepositoryWrapper cloneRemoteRepo() {
		this.getLogger().debug("Creating repository wrapper");
		var gitWrapper = new GitRepositoryWrapper(this.getRepoClonePath().toFile());
		this.getLogger().debug("Created repository wrapper");

		try {
			this.getLogger().debug(String.format("Cloning remote repository (%s) to: %s", this.getRepoURI(),
					gitWrapper.getRootDirectory().toString()));
			gitWrapper.initFromRemoteRepository(this.getRepoURI());
			this.getLogger().debug("Cloning successful");
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail();
		}

		return gitWrapper;
	}

	/**
	 * Prepares local repository copies for each given commit. Requires the remote
	 * repository to be cloned first (see parameter descriptions).
	 * 
	 * @param commits    Commits, for which the repository will be cloned
	 * @param gitWrapper The object that can be used to perform GIT operations on
	 *                   the "main" repository clone, which should be created with
	 *                   {@link #cloneRemoteRepo()}.
	 */
	protected void prepareReposForCommits(List<String> commits, GitRepositoryWrapper gitWrapper) {
		// Checkout and copy local repository clone for each
		// commit except the last one. For the last one, just checkout to that commit to
		// spare 1 copy operation
		this.getLogger().debug("Preparing local repository copies");
		var commitCount = commits.size();
		for (int i = 0; i < commitCount; i++) {
			var commit = commits.get(i);

			this.getLogger().debug(String.format("Checking out: %s", commit));

			try {
				gitWrapper.checkout(commit);
			} catch (GitAPIException e) {
				this.getLogger().debug(String.format("Error while checking out: %s", commit));
				throw new IllegalArgumentException(e);
			}

			this.getLogger().debug(String.format("Checked out"));
			this.getLogger().debug(String.format("Copying for: %s", commit));
			this.copyModels(gitWrapper.getRootDirectory().toPath(), this.getRootDirPath().resolve(commit));

			this.getLogger().debug(String.format("Successfully copied"));
		}
		this.getLogger().debug("Local repository copies are ready");
	}

	/**
	 * Closes the wrapper object, which enabled executing commands on the "main"
	 * local repository clone (created by {@link #cloneRemoteRepo()}.
	 */
	protected void closeGitWrapper(GitRepositoryWrapper gitWrapper) {
		this.getLogger().debug("Closing repository wrapper");
		gitWrapper.closeRepository();
		this.getLogger().debug("Closed repository wrapper");
	}

	/**
	 * Recursively cleans files, which have been used in tests.
	 * 
	 * @param path The path to the directory to clean
	 */
	protected void cleanModels(Path path) {
		this.getFileUtil().cleanModels(path);
	}

	/**
	 * Recursively copies files from the given parent parameter to the path given
	 * via copyPath. Replaces files, which already exist.
	 * 
	 * @param parentPath The directory to copy
	 * @param copyPath   The path, where everything under parentPath will be copied.
	 */
	protected void copyModels(Path parentPath, Path copyPath) {
		this.getFileUtil().copyModels(parentPath, copyPath);
	}

	@Override
	protected Path getRootDirPath() {
		return super.getRootDirPath().resolve(repoModelImplDirName).resolve(this.getRepoName());
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		return this.getCommitIDs().stream().anyMatch((c) -> s.equals(c));
	}

	@Override
	protected boolean isResourceRelevant(Path sourcePath, Resource r) {
		var pathString = sourcePath.toString();
		return this.getCommitIDs().stream().anyMatch((c) -> pathString.contains(c));
	}

	/**
	 * Defaults to {@code rootDirPath/copyDirName}.
	 * 
	 * @return The path, at which the repository clone resides, OR the path, where
	 *         the repository will be cloned to.
	 * 
	 * @see {@link #getRootDirPath()}
	 */
	protected Path getRepoClonePath() {
		return this.getRootDirPath().resolve(copyDirName);
	}

	/**
	 * @return A list of all commits from the repository of this test, which are
	 *         relevant.
	 * 
	 * @see {@link #getRepoURI()}
	 */
	protected abstract List<String> getCommitIDs();

	/**
	 * @return The URI to the repository to be cloned (as String).
	 */
	protected abstract String getRepoURI();

	/**
	 * @return The name of the repository that is relevant for this test.
	 */
	protected abstract String getRepoName();

}
