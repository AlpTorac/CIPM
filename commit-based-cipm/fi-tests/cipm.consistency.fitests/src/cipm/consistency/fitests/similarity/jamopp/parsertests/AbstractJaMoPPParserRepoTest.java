package cipm.consistency.fitests.similarity.jamopp.parsertests;

import cipm.consistency.commitintegration.GitRepositoryWrapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractJaMoPPParserRepoTest extends AbstractJaMoPPParserSimilarityTestFactory {
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
			try {
				this.getLogger().debug(String.format("Checking out: %s", commit));
				gitWrapper.checkout(commit);
				this.getLogger().debug(String.format("Checked out"));
				this.getLogger().debug(String.format("Copying for: %s", commit));
				this.copyModels(gitWrapper.getRootDirectory().toPath(),
						Paths.get(this.getRootDirPath().toString(), commit));

			} catch (Exception e) {
				e.printStackTrace();
				Assertions.fail();
			}
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
		var file = path.toFile();

		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
				return;
			}

			if (file.isDirectory()) {
				var children = file.listFiles();

				if (children != null) {
					for (File cf : children) {
						this.cleanModels(cf.toPath());
					}
				}

				file.delete();
			}
		}
	}

	/**
	 * Recursively copies files from the given parent parameter to the path given
	 * via copyPath. Replaces files, which already exist.
	 * 
	 * @param parentPath The directory to copy
	 * @param copyPath   The path, where everything under parentPath will be copied.
	 */
	protected void copyModels(Path parentPath, Path copyPath) throws IOException {
		File parent = parentPath.toFile();
		this.getLogger().debug("Copying the contents of " + parent.getAbsolutePath() + " into " + copyPath);
		for (File f : parent.listFiles()) {
			var fileName = f.getName();

			// Skip non-java files, since they are irrelevant
			if ((f.isDirectory() && fileName.contains(".git")) || (f.isFile() && !fileName.contains(".java"))) {
				continue;
			}

			if (f.isDirectory()) {
				this.getLogger().debug("Directory found: " + fileName);
				String newCopyAddress = copyPath + File.separator + fileName;
				this.getLogger().debug("Copy address changed to " + newCopyAddress);
				File tmpDir = new File(newCopyAddress);

				this.copyModels(f.toPath(), tmpDir.toPath());
			}
			if (f.isFile()) {
				this.getLogger().debug("File found: " + fileName);
				File tmpFile = new File(copyPath + File.separator + fileName);

				if (tmpFile.exists()) {
					this.getLogger().debug("Existing file will be replaced");
				} else {
					this.getLogger().debug("Creating file");
					tmpFile.mkdirs();
					this.getLogger().debug("Created file");
				}

				this.getLogger().debug("Copying original file into new file");
				Files.copy(f.toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				this.getLogger().debug("Copied original file into new file: " + fileName);

				this.getLogger().debug("Verifying equality of file content");
				Assertions.assertTrue(Files.readString(f.toPath()).equals(Files.readString(tmpFile.toPath())));
				this.getLogger().debug("Verified equality of file content");
			}
		}
		this.getLogger().debug("Parent directory " + parent.getName() + " has been copied");
	}

	@Override
	protected Path getRootDirPath() {
		return Paths.get(super.getRootDirPath().toString(), repoModelImplDirName, this.getRepoName());
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
	 * Defaults to {@code rootDirPath/copyDirName}.
	 * 
	 * @return The path, at which the repository clone resides, OR the path, where
	 *         the repository will be cloned to.
	 * 
	 * @see {@link #getRootDirPath()}
	 */
	protected Path getRepoClonePath() {
		return Paths.get(this.getRootDirPath().toString(), copyDirName);
	}
}
