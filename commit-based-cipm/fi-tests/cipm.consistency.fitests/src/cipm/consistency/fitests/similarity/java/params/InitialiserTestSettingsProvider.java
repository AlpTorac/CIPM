package cipm.consistency.fitests.similarity.java.params;

public class InitialiserTestSettingsProvider {
	private static InitialiserTestSettingsProvider instance;
	private static IInitialiserParameters params;
	private static ISimilarityValues simVals;
	
	private InitialiserTestSettingsProvider() {};
	
	public static void initialise() {
		if (instance == null)
			instance = new InitialiserTestSettingsProvider();
	}
	
	public static InitialiserTestSettingsProvider getInstance() {
		return instance;
	}
	
	public static ISimilarityValues getSimilarityValues() {
		return simVals;
	}
	
	public static void setSimilarityValues(ISimilarityValues similarityValues) {
		simVals = similarityValues;
	}
	
	public static IInitialiserParameters getParameters() {
		return params;
	}
	
	public static void setParameters(IInitialiserParameters prms) {
		params = prms;
	}
	
	public static void reset() {
		params = null;
		simVals = null;
	}
}
