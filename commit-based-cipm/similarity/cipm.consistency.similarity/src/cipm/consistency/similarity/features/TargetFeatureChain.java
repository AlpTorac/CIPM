package cipm.consistency.similarity.features;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class TargetFeatureChain {
	private final List<TargetFeature> targetFeats;

	public TargetFeatureChain(List<TargetFeature> targetFeats) {
		this.targetFeats = List.copyOf(targetFeats);
	}

	public boolean hasTargetFeature(TargetFeature targetFeature) {
		return targetFeats.stream().anyMatch((feat) -> feat.equals(targetFeature));
	}

	public boolean isFeatureRelevant(EStructuralFeature feat) {
		return targetFeats.stream().anyMatch((targetFeature) -> targetFeature.isFeatureRelevant(feat));
	}

	public boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		return targetFeats.stream().anyMatch((targetFeature) -> targetFeature.isFeatureRelevant(eCls, feat));
	}

	public boolean isDerivedFeatureRelevant(EClass eCls, String derivedFeatName) {
		return targetFeats.stream().anyMatch((targetFeature) -> targetFeature.getFeatName().equals(derivedFeatName));
	}

	public TargetFeature getFirstFeature() {
		return targetFeats.get(0);
	}

	public TargetFeature getLastFeature() {
		return targetFeats.get(targetFeats.size() - 1);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof TargetFeatureChain))
			return false;

		var castedO = (TargetFeatureChain) obj;

		if (this.targetFeats.size() != castedO.targetFeats.size())
			return false;

		for (int i = 0; i < targetFeats.size(); i++) {
			if (!this.targetFeats.get(i).equals(castedO.targetFeats.get(i))) {
				return false;
			}
		}

		return true;
	}
}
