package cipm.consistency.fitests.similarity.params;

public class InitialiserTestSettingsProvider {
	private static InitialiserTestSettingsProvider instance;
	private IInitialiserParameters params;
	private ISimilarityValues simVals;

	private InitialiserTestSettingsProvider() {
	};

	public static void initialise() {
		if (instance == null)
			instance = new InitialiserTestSettingsProvider();
	}

	public static InitialiserTestSettingsProvider getInstance() {
		initialise();
		return instance;
	}

	public ISimilarityValues getSimilarityValues() {
		return this.simVals;
	}

	public void setSimilarityValues(ISimilarityValues similarityValues) {
		this.simVals = similarityValues;
	}

	public IInitialiserParameters getParameters() {
		return this.params;
	}

	public void setParameters(IInitialiserParameters prms) {
		this.params = prms;
	}

	public void reset() {
		this.params = null;
		this.simVals = null;
	}
}
