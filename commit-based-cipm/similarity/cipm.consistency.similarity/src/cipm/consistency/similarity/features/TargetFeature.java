package cipm.consistency.similarity.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

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

	public abstract Class<?> getFeatType();

	public EClass getFeatEClass() {
		return featEClass;
	}

	public abstract EStructuralFeature getFeat();

	public abstract String getFeatName();
}
