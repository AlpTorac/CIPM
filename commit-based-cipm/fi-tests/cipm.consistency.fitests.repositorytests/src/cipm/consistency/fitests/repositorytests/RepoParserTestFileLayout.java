package cipm.consistency.fitests.repositorytests;

import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;

import cipm.consistency.fitests.similarity.jamopp.parser.ParserTestFileLayout;

public class RepoParserTestFileLayout extends ParserTestFileLayout {
	/**
	 * The name of the root directory of the models
	 */
	private String repoModelImplDirName;

	/**
	 * The name of the folder, where contents of {@link #resultCache} should be
	 * saved. <br>
	 * <br>
	 * Note: This folder does not have to directly contain the contents of
	 * {@link #resultCache}. They may be saved in sub-directories as well.
	 */
	private String expectedSimilarityResultCacheDirName;

	/**
	 * The name of the file (with extension), where contents of {@link #resultCache}
	 * should be saved.
	 */
	private String expectedSimilarityResultCacheFileName;

	private String repoName;

	public RepoParserTestFileLayout() {
		super();
	}

	public RepoParserTestFileLayout(ParserTestFileLayout layout) {
		super(layout);
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public void setRepoModelImplDirName(String repoModelImplDirName) {
		this.repoModelImplDirName = repoModelImplDirName;
	}

	public void setExpectedSimilarityResultCacheDirName(String expectedSimilarityResultCacheDirName) {
		this.expectedSimilarityResultCacheDirName = expectedSimilarityResultCacheDirName;
	}

	public void setExpectedSimilarityResultCacheFileName(String expectedSimilarityResultCacheFileName) {
		this.expectedSimilarityResultCacheFileName = expectedSimilarityResultCacheFileName;
	}

	/**
	 * @return The path to the saved contents of {@link #resultCache}
	 */
	public Path getExpectedSimilarityResultCachePath() {
		return this.getTestFilesSavePath().resolve(expectedSimilarityResultCacheDirName).resolve(this.repoName)
				.resolve(expectedSimilarityResultCacheFileName);
	}

	/**
	 * @return The URI, at which the parsed commit's resource will point at.
	 */
	public URI getModelResourceSaveURIForCommit(String commitID) {
		return URI.createFileURI(this.getModelResourceSaveRootDirectory().toString()).appendSegment(this.repoName)
				.appendSegment(commitID).appendFileExtension(this.getModelResourceFileExtension());
	}

	/**
	 * @return The path, where the given commit should be cloned
	 */
	public Path getRepoClonePathForCommit(String commitID) {
		return this.getModelSourceFileRootDirPath().resolve(commitID);
	}

	/**
	 * @return The URI to the folder, where the given commit should be cloned
	 */
	public URI getRepoCloneURIForCommit(String commitID) {
		return URI.createFileURI(this.getRepoClonePathForCommit(commitID).toString());
	}

	/**
	 * Use this method for root directory, so that the top-most folder of the
	 * repository is not duplicated.
	 * 
	 * @return The top-most directory, where the repositories will be cloned to
	 */
	public Path getRepoClonesDirPath() {
		return this.getTestFilesSavePath().resolve(repoModelImplDirName);
	}

	/**
	 * @implSpec Returns The path, at which the repository clone resides. Meant to
	 *           be used for accessing the local repository clone. Use
	 *           {@link #getRepoClonesDirPath()} while cloning instead, so that the
	 *           top-most folder of the repository is not duplicated.
	 */
	@Override
	public Path getModelSourceFileRootDirPath() {
		return this.getRepoClonesDirPath().resolve(this.repoName);
	}

}
