package cipm.consistency.fitests.repositorytests;

import cipm.consistency.commitintegration.GitRepositoryWrapper;
import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.parser.IJaMoPPParserTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.IterativeTestGenerationStrategy;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractJaMoPPParserRepoTest extends AbstractJaMoPPParserSimilarityTest {
	private static final String gradleWrapperJarPathPattern = ".*?/gradle-wrapper\\.jar";
	/**
	 * The name of the root directory of the models
	 */
	private static final String repoModelImplDirName = "repo-clones";

	@AfterEach
	@Override
	public void tearDown() {
		if (this.shouldDeleteRepositoryClones()) {
			this.getFileUtil().deleteAll(this.getRepoClonePath());
		}

		super.tearDown();
	}

	/**
	 * Adds model resources to cache for all commits relevant for this test. Must be
	 * executed before all tests.
	 * 
	 * @see {@link #getCommitIDs()}
	 */
	protected Collection<Resource> cacheCommitResources() {
		var cachingStartTime = System.nanoTime();

		var commitResources = new ArrayList<Resource>();
		var commitResourcesExist = true;

		for (var cID : this.getCommitIDs()) {
			var targetPath = this.getRepoClonePathForCommit(cID);
			if (!targetPath.toFile().exists()) {
				this.getLogger().debug(String.format("Model resource missing for %s", cID));
				commitResourcesExist = false;
				break;
			}
		}

		if (!commitResourcesExist) {
			this.getLogger().debug(String.format("Some model resources are missing ... preparing them now",
					this.getRootDirPath().toString()));
			var wrapper = this.cloneRepo();

			commitResources.addAll(this.prepareReposForCommits(this.getCommitIDs(), wrapper));

			this.getLogger().debug("Closing repository wrapper");
			var repoCloseTime = System.nanoTime();
			wrapper.closeRepository();
			this.getLogger().debug(
					String.format("Closed repository wrapper (%s seconds)", this.getElapsedSeconds(repoCloseTime)));

			var mainLocalClonePath = wrapper.getRootDirectory().toPath();

			this.getLogger().debug(
					String.format("Cleaning main local repository clone under: %s", mainLocalClonePath.toString()));

			this.getFileUtil().deleteAll(mainLocalClonePath);

			this.getLogger().debug("Cleaned main local repository clone");
		} else {
			for (var cID : this.getCommitIDs()) {
				var targetPath = this.getRepoClonePathForCommit(cID);
				var res = this.loadResource(targetPath);
				this.getCacheUtil().addToCache(targetPath.toString(), res);
				commitResources.add(this.getCacheUtil().getFromCache(targetPath.toString()));
			}
		}

		this.getLogger().debug(String.format("Repository model resources are cached (%s seconds)",
				this.getElapsedSeconds(cachingStartTime)));
		return commitResources;
	}

	/**
	 * Clones the repository desired by this test.
	 * 
	 * @param repoToCloneURI URI to the repository, which should be cloned (local or
	 *                       remote)
	 * @param clonePath      The path to the folder, where the repository under
	 *                       repoToCloneURI will be cloned
	 * 
	 * @return An object that can be used to perform GIT operations on the
	 *         repository clone.
	 */
	protected GitRepositoryWrapper cloneRepo(String repoToCloneURI, Path clonePath) {
		var cloningStartTime = System.nanoTime();
		this.getLogger().debug("Creating repository wrapper");
		var gitWrapper = new GitRepositoryWrapper(clonePath.toFile());
		this.getLogger().debug("Created repository wrapper");

		try {
			this.getLogger().debug(String.format("Cloning remote repository (%s) to: %s", repoToCloneURI,
					gitWrapper.getRootDirectory().toString()));
			gitWrapper.initFromRemoteRepository(repoToCloneURI);
			this.getLogger()
					.debug(String.format("Cloning successful (%s seconds)", this.getElapsedSeconds(cloningStartTime)));
		} catch (Exception e) {
			e.printStackTrace();
			Assertions.fail();
		}

		return gitWrapper;
	}

	protected GitRepositoryWrapper cloneRepo() {
		return this.cloneRepo(this.getRepoURI(), this.getRepoClonePath());
	}

	/**
	 * Adds model resources to cache model resources for each given commit. Requires
	 * the remote repository to be cloned first (see parameter descriptions). <br>
	 * <br>
	 * <b><i>MODIFIES THE URI OF THE PARSED MODEL RESOURCES</i></b>
	 * 
	 * @param commits    Commits for which a model resource will be parsed
	 * @param gitWrapper The object that can be used to perform GIT operations on
	 *                   the "main" repository clone, which should be created with
	 *                   {@link #cloneRepo()}.
	 */
	protected Collection<Resource> prepareReposForCommits(List<String> commits, GitRepositoryWrapper gitWrapper) {
		var repoPreparationStart = System.nanoTime();

		var commitResources = new ArrayList<Resource>();

		// Checkout and copy local repository clone for each
		// commit except the last one. For the last one, just checkout to that commit to
		// spare 1 copy operation
		this.getLogger().debug("Caching model resources for commits");
		var commitCount = commits.size();
		for (int i = 0; i < commitCount; i++) {
			var commit = commits.get(i);

			var checkoutStartTime = System.nanoTime();
			this.getLogger().debug(String.format("Checking out: %s", commit));

			try {
				gitWrapper.checkout(commit);
			} catch (GitAPIException e) {
				this.getLogger().debug(String.format("Error while checking out: %s", commit));
				throw new IllegalArgumentException(e);
			}

			this.getLogger().debug(
					String.format("Checked out: %s (%s seconds)", commit, this.getElapsedSeconds(checkoutStartTime)));

			this.getLogger().debug(String.format("Caching resource for: %s", commit));
			var cachingStartTime = System.nanoTime();

			var targetPath = this.getRepoClonePathForCommit(commit);
			Resource commitRes = null;

			if (targetPath.toFile().exists()) {
				commitRes = this.parseModelsDirWithCaching(targetPath);
			} else {
				commitRes = this.parseModelsDirWithCaching(gitWrapper.getRootDirectory().toPath(),
						targetPath.toString());
				commitRes.setURI(this.getTestModelSaveURIForCommit(commit));
			}

			commitResources.add(commitRes);
			this.getLogger().debug(String.format("Cached resource for: %s (%s seconds)", commit,
					this.getElapsedSeconds(cachingStartTime)));
		}
		this.getLogger().debug(String.format("Prepared model resources for commits (%s seconds)",
				this.getElapsedSeconds(repoPreparationStart)));
		return commitResources;
	}

	/**
	 * @return The URI, at which the parsed commit's resource will point at.
	 */
	protected URI getTestModelSaveURIForCommit(String commitID) {
		return URI.createFileURI(this.getTestModelSaveRootDirectory().toString()).appendSegment(this.getRepoName())
				.appendSegment(commitID).appendFileExtension(this.getResourceFileExtension());
	}

	/**
	 * @return The path, where the given commit should be cloned
	 */
	protected Path getRepoClonePathForCommit(String commitID) {
		return this.getRootDirPath().resolve(commitID);
	}

	/**
	 * @return The URI to the folder, where the given commit should be cloned
	 */
	protected URI getRepoCloneURIForCommit(String commitID) {
		return URI.createFileURI(this.getRepoClonePathForCommit(commitID).toString());
	}

	@Override
	protected Path getRootDirPath() {
		return super.getRootDirPath().resolve(repoModelImplDirName).resolve(this.getRepoName());
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		return this.getCommitIDs().stream().anyMatch((c) -> s.equals(c));
	}

	/**
	 * Defaults to {@code rootDirPath/repoName}.
	 * 
	 * @return The path, at which the repository clone resides, OR the path, where
	 *         the repository will be cloned to.
	 * 
	 * @see {@link #getRootDirPath()}
	 */
	protected Path getRepoClonePath() {
		return this.getRootDirPath().resolve(this.getRepoName());
	}

	@Override
	protected void setUpModelParser(JaMoPPJDTSingleFileParser parser) {
		parser.setExclusionPatterns(gradleWrapperJarPathPattern);
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

	/**
	 * Defaults to true.
	 * 
	 * @return Whether all cloned repositories that are used by this test class
	 *         should be removed.
	 */
	public boolean shouldDeleteRepositoryClones() {
		return true;
	}

	@Override
	protected Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies() {
		var strats = new ArrayList<IJaMoPPParserTestGenerationStrategy>();
		strats.add(new IterativeTestGenerationStrategy());
		return strats;
	}
}
