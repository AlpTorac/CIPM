package cipm.consistency.fitests.similarity;

public abstract class AbstractSimilarityCheckerContainer implements ISimilarityCheckerContainer {
	private ISimilarityCheckerProvider scp;

	@Override
	public void setSimilarityCheckerProvider(ISimilarityCheckerProvider scp) {
		this.scp = scp;
	}

	@Override
	public ISimilarityCheckerProvider getSimilarityCheckerProvider() {
		return this.scp;
	}
}