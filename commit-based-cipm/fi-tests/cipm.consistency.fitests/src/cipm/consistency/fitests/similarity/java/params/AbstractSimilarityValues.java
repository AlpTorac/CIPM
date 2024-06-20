package cipm.consistency.fitests.similarity.java.params;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSimilarityValues implements ISimilarityValues {
	private Map<Object, Boolean> similarityValues;
	
	public AbstractSimilarityValues() {
		this.similarityValues = this.initSimilarityValues();
	}
	
	protected Map<Object, Boolean> initSimilarityValues() {
		return new HashMap<Object, Boolean>();
	}
	
	public void addSimilarityEntry(Object attr, Boolean expectedSimResult) {
		this.similarityValues.put(attr, expectedSimResult);
	}
	
	public void removeSimilarityEntry(Object attr) {
		this.similarityValues.remove(attr);
	}
	
	public Boolean getExpectedSimilarityResult(Object attr) {
		return this.similarityValues.containsKey(attr) ?
				this.similarityValues.get(attr) : null;
	}
	
	public void clear() {
		this.similarityValues.clear();
	}
}
