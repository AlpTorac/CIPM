package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.similarity.templates.SimilarityCheckingTemplateMethods;

public abstract class TargetFeature {
	private final EClass featEClass;

	protected TargetFeature(EClass featEClass) {
		this.featEClass = featEClass;
	}

	public abstract boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat);

	public boolean isFeatureRelevant(EStructuralFeature feat) {
		return isFeatureRelevant(getFeatEClass(), feat);
	}

	public abstract boolean isDerivedFeature();

	public abstract boolean isManyFeature();

	protected abstract Object computeFeatureValueForObj(EObject obj);

	public FeatureResult computeFeatureValue(EObject obj) {
		if (isManyFeature())
			throw new IllegalStateException("Feature must be single-valued (not many-valued)");
		if (obj.eClass() != getFeatEClass())
			return null;

		return new FeatureResult(this, obj, computeFeatureValueForObj(obj));
	}

	public FeatureResult computeFeatureValueAtIndex(EObject obj, int index) {
		if (!isManyFeature())
			throw new IllegalStateException("Feature must be many-valued");
		return new FeatureResult(this, obj, index,
				SimilarityCheckingTemplateMethods.getElementAtIndex(computeFeatureValueForObj(obj), index));
	}

	public List<FeatureResult> computeAllFeatureValues(EObject obj) {
		var results = new ArrayList<FeatureResult>();

		var computedVal = computeFeatureValueForObj(obj);

		if (SimilarityCheckingTemplateMethods.isObjectManyValued(computedVal)) {
			var featResults = SimilarityCheckingTemplateMethods.getAllElements(computedVal);
			if (!featResults.isEmpty() && EObject.class.isAssignableFrom(featResults.get(0).getClass())) {
				for (int i = 0; i < featResults.size(); i++) {
					// Many-valued feature value that is of type EObject
					results.add(new FeatureResult(this, obj, i, featResults.get(i)));
				}
			} else {
				// Many-valued feature value that is not of type EObject
				results.add(new FeatureResult(this, obj, -1, computedVal));
			}
		} else {
			// Single-valued feature value
			results.add(new FeatureResult(this, obj, -1, computedVal));
		}

		return results;
	}

	public EClass getFeatEClass() {
		return featEClass;
	}

	public abstract EStructuralFeature getFeat();

	public abstract String getFeatName();
}
