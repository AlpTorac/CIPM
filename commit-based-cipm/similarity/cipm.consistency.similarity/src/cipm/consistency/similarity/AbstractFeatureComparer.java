package cipm.consistency.similarity;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.similarity.features.TargetFeatureChain;
import cipm.consistency.similarity.templates.SimilarityCheckingTemplateMethods;

public abstract class AbstractFeatureComparer {
	private TargetFeatureChain targetFeatureChain;

	public AbstractFeatureComparer(TargetFeatureChain targetFeatureChain) {
		this.targetFeatureChain = targetFeatureChain;
	}

	public abstract Boolean compareEObjects(EObject obj1, EObject obj2);
}
