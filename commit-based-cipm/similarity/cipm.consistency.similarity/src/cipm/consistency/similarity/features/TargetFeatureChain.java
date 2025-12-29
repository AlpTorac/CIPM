package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class TargetFeatureChain {
	private final List<TargetFeature> targetFeats;

	public TargetFeatureChain(List<TargetFeature> targetFeats) {
		this.targetFeats = List.copyOf(targetFeats);
	}

	public boolean hasTargetFeature(TargetFeature targetFeature) {
		return targetFeats.stream().anyMatch((feat) -> feat.equals(targetFeature));
	}

	public boolean hasFeature(EStructuralFeature feat) {
		return targetFeats.stream().anyMatch((targetFeature) -> targetFeature.getFeat().equals(feat));
	}

	public boolean hasFeature(EClass eCls, EStructuralFeature feat) {
		return targetFeats.stream().anyMatch(
				(targetFeature) -> targetFeature.getFeatEClass().equals(eCls) && targetFeature.getFeat().equals(feat));
	}

	public TargetFeature getFirstFeature() {
		return targetFeats.get(0);
	}

	public TargetFeature getLastFeature() {
		return targetFeats.get(targetFeats.size() - 1);
	}

	public FeatureResultChain computeFeatureChainValue(EObject obj) {
		return computeFeatureChainValueAtIndices(obj);
	}

	/**
	 * @param obj     EObject whose features' values will be used
	 * @param indices For each many-valued feature (including the derived features)
	 * @return The result of performing chains of {@code value.eGet(feat_i)}
	 *         operations, while continuing with the given indices for many-valued
	 *         features
	 */
	public FeatureResultChain computeFeatureChainValueAtIndices(EObject obj, int... indices) {
		var featVals = new ArrayList<FeatureResult>();
		Object val = obj;
		FeatureResult currentFR = null;

		for (int i = 0, j = 0; i < targetFeats.size(); i++) {
			var currentFeat = targetFeats.get(i);
			if (currentFeat.isManyFeature()) {
				currentFR = currentFeat.computeFeatureValueAtIndex((EObject) val, indices[j]);
				j++;
			} else {
				currentFR = currentFeat.computeFeatureValue((EObject) val);
			}
			featVals.add(currentFR);
			val = currentFR.getTargetFeatureValue();
		}
		return new FeatureResultChain(featVals);
	}

	/**
	 * @return All results of performing chains of {@code value.eGet(feat_i)}
	 *         operations. The returned list contains each final feature value (i.e.
	 *         the value of the feature at the end of the feature chain). Since
	 *         many-valued features lead to multiple results, computes each possible
	 *         outcome in order and returns a list thereof.
	 */
	public List<FeatureResultChain> computeAllFeatureChainValues(EObject obj) {
		return computeAllFeatureChainValues(obj, 0, List.of());
	}

	private List<FeatureResultChain> computeAllFeatureChainValues(Object currentFeatureValue, int currentFeatureIndex,
			List<FeatureResult> featureChainValues) {
		if (targetFeats.size() == currentFeatureIndex) {
			return List.of(new FeatureResultChain(featureChainValues));
		}

		var featureResultChains = new ArrayList<FeatureResultChain>();

		var currentFeat = targetFeats.get(currentFeatureIndex);
		var featureValues = currentFeat.computeAllFeatureValues((EObject) currentFeatureValue);

		for (var fv : featureValues) {
			var nextFeatureValues = new ArrayList<>(featureChainValues);
			nextFeatureValues.add(fv);
			var nextFeatureChainValues = computeAllFeatureChainValues(fv.getTargetFeatureValue(),
					currentFeatureIndex + 1, nextFeatureValues);
			featureResultChains.addAll(nextFeatureChainValues);
		}

		return featureResultChains;
	}
}
