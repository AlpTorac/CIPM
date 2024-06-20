package cipm.consistency.fitests.similarity.java.params;

public interface ISimilarityValues {
	public void addSimilarityEntry(Object attr, Boolean breaksSimilarity);
	public void removeSimilarityEntry(Object attr);
	public void clear();
	
	/**
	 * @return True, if a difference in attr does not break similarity;
	 * False if a difference in attr does break similarity;
	 * Null if unspecified.
	 */
	public Boolean getExpectedSimilarityResult(Object attr);
}
