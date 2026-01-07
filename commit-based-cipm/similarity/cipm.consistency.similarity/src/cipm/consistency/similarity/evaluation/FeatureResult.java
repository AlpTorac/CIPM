package cipm.consistency.similarity.evaluation;

import org.eclipse.emf.ecore.EObject;

import cipm.consistency.similarity.features.TargetFeature;

public class FeatureResult {
	private final TargetFeature targetFeature;
	private final EObject targetFeatureEObject;
	private final int targetFeatureValueIndex;

	private final Object targetFeatureValue;

	public FeatureResult(TargetFeature targetFeature, EObject targetFeatureEObject, int targetFeatureValueIndex,
			Object targetFeatureValue) {
		this.targetFeature = targetFeature;
		this.targetFeatureEObject = targetFeatureEObject;
		this.targetFeatureValueIndex = targetFeatureValueIndex;
		this.targetFeatureValue = targetFeatureValue;
	}

	public FeatureResult(TargetFeature targetFeature, EObject targetFeatureEObject, Object targetFeatureValue) {
		this(targetFeature, targetFeatureEObject, -1, targetFeatureValue);
	}

	public TargetFeature getTargetFeature() {
		return targetFeature;
	}

	public Object getTargetFeatureValue() {
		return targetFeatureValue;
	}

	public EObject getTargetFeatureEObject() {
		return targetFeatureEObject;
	}

	public int getTargetFeatureValueIndex() {
		return targetFeatureValueIndex;
	}
}
