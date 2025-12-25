package cipm.consistency.similarity.features;

import java.util.List;

public class TargetFeatureGroup {
	private final List<TargetFeatureChain> targetFeatChains;
	
	public TargetFeatureGroup(List<TargetFeatureChain> targetFeatChains) {
		this.targetFeatChains = List.copyOf(targetFeatChains);
	}
}
