package cipm.consistency.similarity;

import java.util.LinkedHashMap;
import java.util.Map;

import cipm.consistency.similarity.features.TargetFeatureChain;

public final class SimilarityCheckerConfig {
	private static final Map<TargetFeatureChain, AbstractFeatureComparer> comparisonOps = new LinkedHashMap<>();

	/*
	 * TODO Enumerate relevant TargetFeatureChains. Note that the sub-chains might
	 * be irrelevant. Keep in mind that expanding all of them till getting a
	 * non-EObject value is not possible, due to recursive definitions (ex:
	 * TypeReferences, TypeParameters)
	 * 
	 * TODO Implement exemplary AbstractFeatureComparers. Start with non-EObject
	 * values (i.e. literals)
	 */
}
