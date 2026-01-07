package cipm.consistency.similarity.features;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class TargetFeatureGroup {
	private final List<TargetFeatureChain> targetFeatChains;

	public TargetFeatureGroup(List<TargetFeatureChain> targetFeatChains) {
		this.targetFeatChains = List.copyOf(targetFeatChains);
	}

	public List<TargetFeatureChain> getTargetFeatureChains() {
		return List.copyOf(targetFeatChains);
	}

	public boolean hasTargetFeature(TargetFeature tf) {
		return this.targetFeatChains.stream().anyMatch((tfc) -> tfc.hasTargetFeature(tf));
	}

	public boolean isFeatureRelevant(EStructuralFeature feat) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isFeatureRelevant(feat));
	}

	public boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isFeatureRelevant(eCls, feat));
	}

	public boolean isDerivedFeatureRelevant(EClass eCls, String derivedFeatName) {
		return targetFeatChains.stream().anyMatch((tfc) -> tfc.isDerivedFeatureRelevant(eCls, derivedFeatName));
	}
}
