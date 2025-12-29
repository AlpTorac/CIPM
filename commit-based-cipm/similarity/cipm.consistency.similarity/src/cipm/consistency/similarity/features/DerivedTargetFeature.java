package cipm.consistency.similarity.features;

import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DerivedTargetFeature extends TargetFeature {
	private final List<EStructuralFeature> derivedFeatureComponents;
	private final String derivedFeatureName;
	private final Function<EObject, Object> derivedFeatureComputationAlgorithm;

	private final boolean isMany;

	public DerivedTargetFeature(EClass featEClass, List<EStructuralFeature> derivedFeatureComponents,
			Function<EObject, Object> derivedFeatureComputationAlgorithm, boolean isMany, String derivedFeatureName) {
		super(featEClass);

		this.isMany = isMany;

		if (derivedFeatureComponents != null) {
			this.derivedFeatureComponents = List.copyOf(derivedFeatureComponents);
		} else {
			this.derivedFeatureComponents = null;
		}

		this.derivedFeatureName = derivedFeatureName;
		this.derivedFeatureComputationAlgorithm = derivedFeatureComputationAlgorithm;
	}

	public DerivedTargetFeature(EClass featEClass, Function<EObject, Object> derivedFeatureComputationAlgorithm,
			String derivedFeatureName) {
		this(featEClass, null, derivedFeatureComputationAlgorithm, false, derivedFeatureName);
	}

	@Override
	public boolean isDerivedFeature() {
		return true;
	}

	@Override
	protected Object computeFeatureValueForObj(EObject obj) {
		return derivedFeatureComputationAlgorithm.apply(obj);
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
	public boolean isManyFeature() {
		return isMany;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DerivedTargetFeature))
			return false;
		var castedO = (DerivedTargetFeature) obj;

		return this.getFeatEClass().equals(castedO.getFeatEClass())
				&& this.derivedFeatureName.equals(castedO.derivedFeatureName);
	}
}
