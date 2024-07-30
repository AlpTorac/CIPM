package cipm.consistency.fitests.similarity.java.params;

public class InitialiserTestSettingsProvider {
	private static InitialiserTestSettingsProvider instance;
	private static IInitialiserParameters params;
	private static ISimilarityValues simVals;

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
		return simVals;
	}

	public void setSimilarityValues(ISimilarityValues similarityValues) {
		simVals = similarityValues;
	}

	public IInitialiserParameters getParameters() {
		return params;
	}

	public void setParameters(IInitialiserParameters prms) {
		params = prms;
	}

	public void reset() {
		params = null;
		simVals = null;
	}
}
