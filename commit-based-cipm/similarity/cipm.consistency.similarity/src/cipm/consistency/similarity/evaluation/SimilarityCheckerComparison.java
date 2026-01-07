package cipm.consistency.similarity.evaluation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.similarity.features.TargetFeatureGroup;

public class SimilarityCheckerComparison {
	private final EObject obj1;
	private final EObject obj2;
	private final TargetFeatureGroup features;

	private final SimilarityCheckerComparison parentComparison;
	private final List<SimilarityCheckerComparison> childComparisons;

	protected SimilarityCheckerComparison(SimilarityCheckerComparison parentComparison,
			List<SimilarityCheckerComparison> childComparisons, EObject obj1, EObject obj2,
			TargetFeatureGroup features) {
		this.parentComparison = parentComparison;
		this.childComparisons = childComparisons;
		this.obj1 = obj1;
		this.obj2 = obj2;
		this.features = features;
	}
	
	public SimilarityCheckerComparison(EObject obj1, EObject obj2, TargetFeatureGroup features) {
		this(null, new ArrayList<>(), obj1, obj2, features);
	}
}
