package cipm.consistency.similarity.features;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DerivedTargetFeature extends TargetFeature {
	private final List<EStructuralFeature> derivedFeatureComponents;
	private final String derivedFeatureName;
	private final Class<?> featType;

	public DerivedTargetFeature(EClass featEClass, Class<?> featType, List<EStructuralFeature> derivedFeatureComponents,
			String derivedFeatureName) {
		super(featEClass);

		this.featType = featType;

		if (derivedFeatureComponents != null) {
			this.derivedFeatureComponents = List.copyOf(derivedFeatureComponents);
		} else {
			this.derivedFeatureComponents = null;
		}

		this.derivedFeatureName = derivedFeatureName;
	}

	public DerivedTargetFeature(EClass featEClass, Class<?> featType, String derivedFeatureName) {
		this(featEClass, featType, null, derivedFeatureName);
	}

	@Override
	public boolean isDerivedFeature() {
		return true;
	}

	@Override
	public EStructuralFeature getFeat() {
		return null;
	}

	@Override
	public String getFeatName() {
		return derivedFeatureName;
	}

	public List<EStructuralFeature> getDerivedFeatureComponents() {
		return List.copyOf(derivedFeatureComponents);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DerivedTargetFeature))
			return false;
		var castedO = (DerivedTargetFeature) obj;

		return this.getFeatEClass().equals(castedO.getFeatEClass())
				&& this.derivedFeatureName.equals(castedO.derivedFeatureName);
	}

	@Override
	public boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		return this.getFeatEClass().equals(eCls)
				&& this.derivedFeatureComponents.stream().anyMatch((f) -> f.equals(feat));
	}

	@Override
	public Class<?> getFeatType() {
		return featType;
	}
}
