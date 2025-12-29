package cipm.consistency.similarity.features;

import java.util.List;

public class FeatureResultChain {
	private final List<FeatureResult> targetFeatureResults;

	public FeatureResultChain(List<FeatureResult> targetFeats) {
		this.targetFeatureResults = List.copyOf(targetFeats);
	}

	public FeatureResult getLastFeatureResult() {
		return targetFeatureResults.get(targetFeatureResults.size() - 1);
	}
	
	public int getFeatureResultCount() {
		return targetFeatureResults.size();
	}

	public FeatureResult getFeatureResultAtIndex(int index) {
		return targetFeatureResults.get(index);
	}
}
