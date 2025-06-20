package cipm.consistency.fitests.repositorytests;

import cipm.consistency.fitests.repositorytests.util.RepoTestResultCache;
import cipm.consistency.fitests.repositorytests.util.RepoTestSimilarityValueEstimator;
import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.parser.IJaMoPPParserTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.IModelResourceWrapper;
import cipm.consistency.fitests.similarity.jamopp.parser.ReflexiveSymmetricIterationTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.ModelResourceWrapper;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * An abstract test class, which can be used for implementing tests that involve
 * parsing models from GIT repositories and checking their similarity.
 * 
 * @author Alp Torac Genc
 * 
 * @see {@link AbstractJaMoPPParserSimilarityTest#createTests()}
 */
public abstract class AbstractJaMoPPParserRepoTest extends AbstractJaMoPPParserSimilarityTest {
	// TODO Extract parsing logic

	// TODO Allow overriding expected results of tests

	// TODO Simplify methods that return paths and URIs

	// TODO Improve time measuring

	/**
	 * Contains expected results of comparing model resources
	 */
	private static RepoTestResultCache resultCache = new RepoTestResultCache();

	/**
	 * The pattern of "gradle-wrapper.jar" file path, which should be excluded when
	 * parsing model resources to avoid IOExceptions.
	 */
	private static final String gradleWrapperJarPathPattern = ".*?/gradle-wrapper\\.jar";

	/**
	 * The name of the root directory of the models
	 */
	private static final String repoModelImplDirName = "repo-clones";

	/**
	 * The segment in remote GIT repository URLs, which are followed by the commit
	 * hash
	 */
	private static final String repoURICommitSegment = "commit";

	/**
	 * The name of the folder, where contents of {@link #resultCache} should be
	 * saved. <br>
	 * <br>
	 * Note: This folder does not have to directly contain the contents of
	 * {@link #resultCache}. They may be saved in sub-directories as well.
	 */
	private static final String expectedSimilarityResultCacheDirName = "results-cache";

	/**
	 * The name of the file (with extension), where contents of {@link #resultCache}
	 * should be saved.
	 */
	private static final String expectedSimilarityResultCacheFileName = "resultsCache.json";

	@BeforeEach
	@Override
	public void setUp(TestInfo info) {
		super.setUp(getCurrentTestInfo());

		var resultCachePath = this.getExpectedSimilarityResultCachePath();
		if (this.shouldUseCachedExpectedSimilarityResults()) {

			this.getLogger().debug(String.format("Checking for cached expected similarity results for %s at %s",
					this.getCurrentTestClassName(), resultCachePath));
			if (resultCachePath.toFile().exists()) {
				this.getLogger().debug(String.format("Cached expected similarity results exist"));
				try (BufferedReader reader = Files.newBufferedReader(resultCachePath)) {
					this.getLogger().debug(String.format("Reading cached expected similarity results"));
					resultCache = new RepoTestResultCache(new Gson().fromJson(reader, resultCache.getClass()));
					this.getLogger().debug(String.format("Read cached expected similarity results"));
				} catch (IOException e) {
					this.getLogger()
							.debug(String.format("Could not read cached expected similarity results for %s at %s",
									this.getCurrentTestClassName(), resultCachePath));
				}
			}
		} else {
			this.getLogger().debug(String.format("No saved expected similarity results found for %s at %s",
					this.getCurrentTestClassName(), resultCachePath));
		}
	}

	@AfterEach
	@Override
	public void tearDown() {
		if (this.shouldSaveCachedExpectedSimilarityResults()) {
			var gson = new GsonBuilder().setPrettyPrinting().create();

			var resultCachePath = this.getExpectedSimilarityResultCachePath();
			var resultCacheFile = resultCachePath.toFile();

			this.getLogger().debug(String.format("Saving cached expected similarity results for %s at %s",
					this.getCurrentTestClassName(), resultCachePath));

			// Re-write expected similarity results

			if (resultCacheFile.exists()) {
				resultCacheFile.delete();
			}
			resultCacheFile.getParentFile().mkdirs();
			try {
				resultCacheFile.createNewFile();
			} catch (IOException e) {
				Assertions.fail(String.format("Could not create a file for result cache at %s", resultCachePath), e);
			}

			try (BufferedWriter writer = Files.newBufferedWriter(resultCachePath);
					var gsonWriter = gson.newJsonWriter(writer)) {
				gson.toJson(resultCache, resultCache.getClass(), gsonWriter);
			} catch (IOException e) {
				Assertions.fail(String.format("Could not save the expected similarity results at %s", resultCachePath),
						e);
			}

			this.getLogger().debug(String.format("Saved cached expected similarity results for %s at %s",
					this.getCurrentTestClassName(), resultCachePath));
		}

		if (this.shouldDeleteRepositoryClones()) {
			this.getFileUtil().deleteAll(this.getRootDirPath());
		}

		super.tearDown();
	}

	/**
	 * @return The path to the saved contents of {@link #resultCache}
	 */
	protected Path getExpectedSimilarityResultCachePath() {
		return this.getAbsoluteCurrentDirectory().resolve(expectedSimilarityResultCacheDirName)
				.resolve(this.getRepoName()).resolve(expectedSimilarityResultCacheFileName);
	}

	/**
	 * @return Whether the expected similarity checking result for the given commits
	 *         it present in {@link #resultCache}. Note that similarity checking is
	 *         symmetric, meaning that swapping lhs and rhs commits should not
	 *         change the return value.
	 */
	protected boolean isExpectedResultPresent(String lhsCommit, String rhsCommit) {
		return resultCache.isInCache(lhsCommit, rhsCommit);
	}

	/**
	 * @return The expected similarity checking result for the given commits. Note
	 *         that similarity checking is symmetric, meaning that swapping lhs and
	 *         rhs commits should not change the return value.
	 */
	protected Boolean getExpectedResult(String lhsCommit, String rhsCommit) {
		return resultCache.getResult(lhsCommit, rhsCommit);
	}

	/**
	 * Adds model resources to {@link #resultCache} for all commits relevant for
	 * this test. Must be executed before all tests.
	 * 
	 * @see {@link #getCommitIDs()}
	 */
	protected Collection<Resource> cacheCommitResources() {
		var cachingStartTime = System.nanoTime();

		var commitResources = new ArrayList<Resource>();
		var commitResourcesExist = true;
		final var expectedResultsExist = new boolean[] { true };

		var commitIDList = this.getCommitIDs();
		var testStrats = this.getTestGenerationStrategies();

		// TODO Refactor

		/*
		 * Determine whether all required expected results are in the cache based on
		 * what commits are compared to one another in the tests
		 */
		testStrats.forEach((ts) -> ts.getTestResourceIterator(commitIDList.size()).forEachRemaining((idxs) -> {
			var commitID1 = commitIDList.get(idxs[0]);
			var commitID2 = commitIDList.get(idxs[1]);
			if (!resultCache.isInCache(commitID1, commitID2)) {
				this.getLogger()
						.debug(String.format("Expected similarity result missing for: %s vs %s", commitID1, commitID2));
				expectedResultsExist[0] = false;
				// Check for the other ones as well, for debugging purposes
			}
		}));

		for (var cID : commitIDList) {
			if (!this.getResourceHelper().resourceFileExists(this.getTestModelSaveURIForCommit(cID))) {
				this.getLogger().debug(String.format("Model resource missing for: %s", cID));
				commitResourcesExist = false;
				// Check for the other ones as well, for debugging purposes
			}
		}

		Git git = null;

		if (!expectedResultsExist[0] || !commitResourcesExist) {
			this.getLogger()
					.debug("Remote repository must be cloned due to missing resources / expected similarity results");
			git = this.cloneRepo();
		}

		if (!expectedResultsExist[0]) {
			this.getLogger().debug(String.format("Computing missing expected similarity results"));

			this.computeExpectedSimilarityResults(git, commitIDList);

			this.getLogger().debug(String.format("Computed missing similarity results"));
		}

		if (!commitResourcesExist) {
			this.getLogger().debug(String.format("Preparing missing model resources"));

			commitResources.addAll(this.prepareReposForCommits(commitIDList, git));

			this.getLogger().debug(String.format("Prepared missing model resources"));
		} else {
			for (var cID : commitIDList) {
				var cachedCommitURI = this.getTestModelSaveURIForCommit(cID);
				var res = new ModelResourceWrapper(this.getResourceHelper());
				res.loadModelResource(cachedCommitURI);
				this.getCacheUtil().addToCache(cachedCommitURI.toString(), res);
				commitResources.add(this.getCacheUtil().getFromCache(cachedCommitURI.toString()).getModelResource());
			}
		}

		if (git != null) {
			this.getLogger().debug("Closing repository wrapper");

			var repoCloseTime = System.nanoTime();

			git.getRepository().close();
			git.close();

			this.getLogger().debug(
					String.format("Closed repository wrapper (%s seconds)", this.getElapsedSeconds(repoCloseTime)));
		}

		var mainLocalClonePath = this.getRootDirPath();

		this.getLogger()
				.debug(String.format("Cleaning main local repository clone under: %s", mainLocalClonePath.toString()));

		this.getFileUtil().deleteAll(mainLocalClonePath);

		this.getLogger().debug("Cleaned main local repository clone");

		this.getLogger().debug(String.format("Repository model resources are cached (%s seconds)",
				this.getElapsedSeconds(cachingStartTime)));
		return commitResources;
	}

	/**
	 * 
	 * @param git          The GIT object associated with the in-memory
	 *                     representation of the GIT repository
	 * @param commitIDList A list of commit hashes, for which expected similarity
	 *                     results should be computed.
	 */
	protected void computeExpectedSimilarityResults(Git git, List<String> commitIDList) {

		// TODO Account for other test generation strategies (not just for
		// IterativeTestGenerationStrategy)

		var expectedValueEstimator = new RepoTestSimilarityValueEstimator();

		for (int i = 0; i < commitIDList.size() - 1; i++) {
			var commitID1 = commitIDList.get(i);
			var commitID2 = commitIDList.get(i + 1);
			if (!resultCache.isInCache(commitID1, commitID2)) {
				this.getLogger().debug(
						String.format("Computing expected similarity result for: %s vs %s", commitID1, commitID2));
				var result = expectedValueEstimator.getExpectedSimilarityValueFor(git, commitID1, commitID2);

				resultCache.addResult(commitID1, commitID2, result);

				this.getLogger().debug(String.format("Computed expected similarity result (%s) for: %s vs %s", result,
						commitID1, commitID2));
			}
		}
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
	protected Git cloneRepo(String repoToCloneURI, Path clonePath) {
		var cloningStartTime = System.nanoTime();

		Git git = null;

		// Repository clone does not exist, clone it
		if (!clonePath.toFile().exists() || clonePath.toFile().list() == null
				|| clonePath.toFile().list().length == 0) {
			try {
				this.getLogger().debug(
						String.format("Cloning remote repository (%s) to: %s", repoToCloneURI, clonePath.toString()));
				git = Git.cloneRepository().setURI(repoToCloneURI).setDirectory(clonePath.toFile())
						.setCloneAllBranches(true).call();
				this.getLogger().debug(
						String.format("Cloning successful (%s seconds)", this.getElapsedSeconds(cloningStartTime)));
			} catch (GitAPIException e) {
				e.printStackTrace();
				Assertions.fail("Could not clone repository");
			}
		}
		// Repository clone folder exists, try to open it
		// If it does not open, delete it and re-try
		else {
			try {
				git = Git.open(clonePath.toFile());
			} catch (IOException e) {
				// Faulty repository clone, delete and re-try
				this.getLogger().debug("Could not open existing repository, deleting it and re-cloning");
				this.getFileUtil().deleteAll(clonePath);
				if (clonePath.toFile().exists() && clonePath.toFile().list().length != 0) {
					throw new IllegalStateException("Could not delete faulty repository clone");
				}
				git = this.cloneRepo(repoToCloneURI, clonePath);
			}
		}

		return git;
	}

	/**
	 * Calls {@link #cloneRepo()} with default parameters.
	 * 
	 * @see {@link #cloneRepo()}
	 */
	protected Git cloneRepo() {
		// Do not explicitly add a folder for this repository, since GIT will do that
		// implicitly
		return this.cloneRepo(this.getRepoURIAsString(), this.getRootDirPath());
	}

	/**
	 * @return The cache key for the model resource parsed from the given commit
	 *         hash of the repository, when its model resource is inserted into the
	 *         cache via {@link #parseModelsDirWithCaching(Path, URI, String)}.
	 */
	protected String getCacheKeyForCommit(URI repoURI, String commitID) {
		return repoURI.appendSegment(repoURICommitSegment).appendSegment(commitID).toString();
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
	protected Collection<Resource> prepareReposForCommits(List<String> commits, Git git) {
		var repoPreparationStart = System.nanoTime();

		var commitResources = new ArrayList<Resource>();

		// Checkout and copy local repository clone for each
		// commit except the last one. For the last one, just checkout to that commit to
		// spare 1 copy operation
		this.getLogger().debug("Caching model resources for commits");
		var commitCount = commits.size();
		for (int i = 0; i < commitCount; i++) {
			var commitID = commits.get(i);
			var commitResURI = this.getTestModelSaveURIForCommit(commitID);

			var checkoutStartTime = System.nanoTime();
			this.getLogger().debug(String.format("Checking out: %s", commitID));

			try {
				git.checkout().setName(commitID).call();
			} catch (GitAPIException e) {
				this.getLogger().debug(String.format("Error while checking out: %s", commitID));
				throw new IllegalArgumentException(e);
			}

			this.getLogger().debug(
					String.format("Checked out: %s (%s seconds)", commitID, this.getElapsedSeconds(checkoutStartTime)));

			this.getLogger().debug(String.format("Caching resource for: %s", commitID));
			var cachingStartTime = System.nanoTime();

			var targetPath = this.getRepoClonePathForCommit(commitID);
			IModelResourceWrapper commitRes = null;

			/*
			 * Load the cached model resource for the commit, if it exists. Otherwise parse
			 * it.
			 */
			if (targetPath.toFile().exists()) {
				commitRes = this.parseModelsDirWithCaching(targetPath, commitResURI,
						getCacheKeyForCommit(this.getRepoURI(), commitID));
			} else {
				commitRes = this.parseModelsDirWithCaching(git.getRepository().getDirectory().getParentFile().toPath(),
						commitResURI, getCacheKeyForCommit(this.getRepoURI(), commitID));
				commitRes.setModelResourcesURI(commitResURI);
			}

			commitResources.add(commitRes.getModelResource());
			this.getLogger().debug(String.format("Cached resource for: %s (%s seconds)", commitID,
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

	/**
	 * Use this method for root directory, so that the top-most folder of the
	 * repository is not duplicated.
	 * 
	 * @return The top-most directory, where the repositories will be cloned to
	 */
	protected Path getRepoClonesDirPath() {
		return this.getAbsoluteCurrentDirectory().resolve(repoModelImplDirName);
	}

	/**
	 * @implSpec Returns The path, at which the repository clone resides. Meant to
	 *           be used for accessing the local repository clone. Use
	 *           {@link #getRepoClonesDirPath()} while cloning instead, so that the
	 *           top-most folder of the repository is not duplicated.
	 */
	@Override
	protected Path getRootDirPath() {
		return this.getRepoClonesDirPath().resolve(this.getRepoName());
	}

	/**
	 * @implSpec Checks whether the given directory name matches any of the commit
	 *           hashes featured in tests.
	 */
	@Override
	protected boolean isModelDirectoryName(String dirName) {
		return this.getCommitIDs().stream().anyMatch((c) -> dirName.equals(c));
	}

	/**
	 * @implSpec Adds {@value #gradleWrapperJarPathPattern} to exclusion patterns of
	 *           the given parser, in order for that file to not be locked during
	 *           tests. If it were locked, trying to delete it (while deleting the
	 *           local repository clone) does not work and may lead to IOExceptions.
	 */
	@Override
	protected void setUpModelParser(JaMoPPJDTSingleFileParser parser) {
		super.setUpModelParser(parser);
		parser.setExclusionPatterns(gradleWrapperJarPathPattern);
	}

	/**
	 * @return A list of all commits from the repository of this test, which are
	 *         relevant.
	 * 
	 * @see {@link #getRepoURIAsString()}
	 */
	protected abstract List<String> getCommitIDs();

	/**
	 * @return The URI to the repository to be cloned (as String).
	 */
	protected String getRepoURIAsString() {
		return this.getRepoURI().toString();
	}

	/**
	 * @return The URI to the repository, which will be used in tests.
	 */
	protected abstract URI getRepoURI();

	/**
	 * @return The name of the repository that is used in this test.
	 */
	protected String getRepoName() {
		return this.getRepoURI().lastSegment();
	}

	/**
	 * Defaults to true.
	 * 
	 * @return Whether all cloned repositories that are used by this test class
	 *         should be removed.
	 */
	public boolean shouldDeleteRepositoryClones() {
		return true;
	}

	/**
	 * Defaults to true.
	 * 
	 * @return Whether the cached expected similarity results in
	 *         {@link #resultCache} should be saved.
	 */
	public boolean shouldSaveCachedExpectedSimilarityResults() {
		return true;
	}

	/**
	 * Defaults to true.
	 * 
	 * @return Whether the cached expected similarity results in
	 *         {@link #resultCache} should actually be used.
	 */
	public boolean shouldUseCachedExpectedSimilarityResults() {
		return true;
	}

	@Override
	protected Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies() {
		var strats = new ArrayList<IJaMoPPParserTestGenerationStrategy>();
		strats.add(new ReflexiveSymmetricIterationTestGenerationStrategy());
		return strats;
	}
}
