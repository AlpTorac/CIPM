package cipm.consistency.similarity;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;

import cipm.consistency.similarity.features.TargetFeatureGroup;

public final class SimilarityCheckerConfig {
	private static final Map<TargetFeatureGroup, AbstractFeatureComparer> comparisonOps = new LinkedHashMap<>();
}
