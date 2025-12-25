package cipm.consistency.similarity.features;

import java.util.List;

public class TargetFeatureChain {
	private final List<TargetFeature> targetFeats;
	
	public TargetFeatureChain(List<TargetFeature> targetFeats) {
		this.targetFeats = List.copyOf(targetFeats);
	}
}
