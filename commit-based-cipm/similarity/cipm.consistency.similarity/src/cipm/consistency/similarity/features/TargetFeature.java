package cipm.consistency.similarity.features;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class TargetFeature {
	private final EClass featEClass;

	protected TargetFeature(EClass featEClass) {
		this.featEClass = featEClass;
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
		return new FeatureResult(this, obj, index, FeatureUtility.getElementAtIndex(computeFeatureValueForObj(obj), index));
	}

	public List<FeatureResult> computeAllFeatureValues(EObject obj) {
		var results = new ArrayList<FeatureResult>();
		var featResults = FeatureUtility.getAllElements(computeFeatureValueForObj(obj));
		for (int i = 0; i < results.size(); i++) {
			results.add(new FeatureResult(this, obj, i, featResults.get(i)));
		}
		return results;
	}

	public EClass getFeatEClass() {
		return featEClass;
	}

	public abstract EStructuralFeature getFeat();

	public abstract String getFeatName();
}
