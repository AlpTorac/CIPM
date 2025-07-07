package cipm.consistency.fitests.repositorytests;

import cipm.consistency.fitests.similarity.jamopp.parser.ParserTestOptions;

/**
 * TODO Write commentary
 * 
 * @author Alp Torac Genc
 */
public class RepoParserTestOptions extends ParserTestOptions {
	private boolean shouldDeleteRepositoryClones;
	private boolean shouldSaveCachedExpectedSimilarityResults;
	private boolean shouldUseCachedExpectedSimilarityResults;

	public void setShouldDeleteRepositoryClones(boolean shouldDeleteRepositoryClones) {
		this.shouldDeleteRepositoryClones = shouldDeleteRepositoryClones;
	}

	public void setShouldSaveCachedExpectedSimilarityResults(boolean shouldSaveCachedExpectedSimilarityResults) {
		this.shouldSaveCachedExpectedSimilarityResults = shouldSaveCachedExpectedSimilarityResults;
	}

	public void setShouldUseCachedExpectedSimilarityResults(boolean shouldUseCachedExpectedSimilarityResults) {
		this.shouldUseCachedExpectedSimilarityResults = shouldUseCachedExpectedSimilarityResults;
	}

	/**
	 * @return Whether all cloned repositories should be removed.
	 */
	public boolean shouldDeleteRepositoryClones() {
		return shouldDeleteRepositoryClones;
	}

	/**
	 * @return Whether the cached expected similarity results should be saved.
	 */
	public boolean shouldSaveCachedExpectedSimilarityResults() {
		return shouldSaveCachedExpectedSimilarityResults;
	}

	/**
	 * @return Whether the cached expected similarity results should actually be
	 *         used.
	 */
	public boolean shouldUseCachedExpectedSimilarityResults() {
		return shouldUseCachedExpectedSimilarityResults;
	}

	public void copyOptionsFrom(RepoParserTestOptions opts) {
		super.copyOptionsFrom(opts);
		this.shouldDeleteRepositoryClones = opts.shouldDeleteRepositoryClones;
		this.shouldSaveCachedExpectedSimilarityResults = opts.shouldSaveCachedExpectedSimilarityResults;
		this.shouldUseCachedExpectedSimilarityResults = opts.shouldUseCachedExpectedSimilarityResults;
	}
}
