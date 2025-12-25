package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

public class TargetFeatureChain {
	private final List<TargetFeature> targetFeats;

	public TargetFeatureChain(List<TargetFeature> targetFeats) {
		this.targetFeats = List.copyOf(targetFeats);
	}

	/**
	 * @param obj     EObject whose features' values will be used
	 * @param indices For each many-valued feature (including the derived features)
	 * @return The result of performing chains of {@code value.eGet(feat_i)}
	 *         operations, while continuing with the given indices for many-valued
	 *         features
	 */
	public Object computeFeatureChainValueAtIndices(EObject obj, int... indices) {
		Object val = obj;
		for (int i = 0, j = 0; i < targetFeats.size(); i++) {
			var currentFeat = targetFeats.get(i);
			if (currentFeat.isManyFeature()) {
				val = currentFeat.computeFeatureValueAtIndex((EObject) val, indices[j]);
				j++;
			} else {
				val = currentFeat.computeFeatureValue((EObject) val);
			}
		}
		return val;
	}

	/**
	 * @return All results of performing chains of {@code value.eGet(feat_i)}
	 *         operations. The returned list contains each final feature value (i.e.
	 *         the value of the feature at the end of the feature chain). Since
	 *         many-valued features lead to multiple results, computes each possible
	 *         outcome in order and returns a list thereof.
	 */
	public List<Object> computeAllFeatureChainValues(EObject obj) {
		var val = new ArrayList<>();
		computeAllFeatureChainValues(obj, 0, val);
		return val;
	}

	private void computeAllFeatureChainValues(Object currentObj, int currentFeatIdx, List<Object> currentVals) {
		var currentFeat = targetFeats.get(currentFeatIdx);

		if (targetFeats.size() == currentFeatIdx + 1 || !(currentObj instanceof EObject)) {
			currentVals.add(currentObj);
			return;
		}

		var vals = currentFeat.computeAllFeatureValues((EObject) currentObj);
		for (int i = 0; i < vals.size(); i++) {
			computeAllFeatureChainValues(vals.get(i), currentFeatIdx + 1, currentVals);
		}
	}
}
