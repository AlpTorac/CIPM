package cipm.consistency.similarity.evaluation;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.similarity.features.TargetFeatureChain;

public abstract class AbstractFeatureComparer {
	private TargetFeatureChain targetFeatureChain;

	public AbstractFeatureComparer(TargetFeatureChain targetFeatureChain) {
		this.targetFeatureChain = targetFeatureChain;
	}

	public abstract Boolean compareEObjects(EObject obj1, EObject obj2);
}
