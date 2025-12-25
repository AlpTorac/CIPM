package cipm.consistency.similarity.features;

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
	
	public abstract Object computeFeatureValue(EObject obj);
	
	public Object computeFeatureValueAtIndex(EObject obj, int index) {
		if (!isManyFeature())
			throw new IllegalStateException("Feature must be many-valued");
		return FeatureUtility.getElementAtIndex(computeFeatureValue(obj), index);
	}

	public List<Object> computeAllFeatureValues(EObject obj) {
		return FeatureUtility.getAllElements(computeFeatureValue(obj));
	}
	
	public EClass getFeatEClass() {
		return featEClass;
	}

	public abstract EStructuralFeature getFeat();

	public abstract String getFeatName();
}
