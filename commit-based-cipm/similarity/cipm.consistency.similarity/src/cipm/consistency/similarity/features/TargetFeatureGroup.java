package cipm.consistency.similarity.features;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.similarity.templates.SimilarityCheckingTemplateMethods;

public class TargetFeatureGroup {
	private final List<TargetFeatureChain> targetFeatChains;

	public TargetFeatureGroup(List<TargetFeatureChain> targetFeatChains) {
		this.targetFeatChains = List.copyOf(targetFeatChains);
	}

	public boolean compare(EObject obj1, EObject obj2) {
		for (var tfc : targetFeatChains) {
			var vals1 = tfc.computeAllFeatureChainValues(obj1);
			var vals2 = tfc.computeAllFeatureChainValues(obj2);

			if (vals1.size() != vals2.size()) {
				return false;
			}

			for (int i = 0; i < vals1.size(); i++) {
				var val1 = vals1.get(i);
				var val2 = vals2.get(i);
				if (SimilarityCheckingTemplateMethods.compareValue(val1.getLastFeature().getTargetFeatureValue(),
						val2.getLastFeature().getTargetFeatureValue()) != Boolean.TRUE) {
					return false;
				}
			}

		}
		return true;
	}
}
