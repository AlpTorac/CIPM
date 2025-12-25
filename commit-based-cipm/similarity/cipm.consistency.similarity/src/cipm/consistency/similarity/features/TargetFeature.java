package cipm.consistency.similarity.features;

import java.util.List;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class TargetFeature {
	private final EClass featEClass;
	private final EStructuralFeature feat;

	private final List<EStructuralFeature> derivedFeatureComponents;
	private final String derivedFeatureName;
	private final Function<EObject, Object> derivedFeatureComputationAlgorithm;

	protected TargetFeature(EClass featEClass, EStructuralFeature feat,
			List<EStructuralFeature> derivedFeatureComponents,
			Function<EObject, Object> derivedFeatureComputationAlgorithm, String derivedFeatureName) {
		this.featEClass = featEClass;
		this.feat = feat;

		if (derivedFeatureComponents != null) {
			this.derivedFeatureComponents = List.copyOf(derivedFeatureComponents);
		} else {
			this.derivedFeatureComponents = null;
		}

		this.derivedFeatureName = derivedFeatureName;
		this.derivedFeatureComputationAlgorithm = derivedFeatureComputationAlgorithm;
	}

	public TargetFeature(EClass featEClass, EStructuralFeature feat) {
		this(featEClass, feat, null, null, null);
	}

	public TargetFeature(EClass featEClass, Function<EObject, Object> derivedFeatureComputationAlgorithm,
			String derivedFeatureName) {
		this(featEClass, null, null, derivedFeatureComputationAlgorithm, derivedFeatureName);
	}

	public TargetFeature(EClass featEClass, List<EStructuralFeature> derivedFeatureComponents,
			Function<EObject, Object> derivedFeatureComputationAlgorithm, String derivedFeatureName) {
		this(featEClass, null, derivedFeatureComponents, derivedFeatureComputationAlgorithm, derivedFeatureName);
	}

	public boolean isDerived() {
		return derivedFeatureName != null && !derivedFeatureName.isBlank();
	}

	public Object computeFeatureVal(EObject obj) {
		if (obj.eClass() != featEClass)
			return null;

		if (!isDerived())
			return obj.eGet(feat);

		return derivedFeatureComputationAlgorithm.apply(obj);
	}

	public EClass getFeatEClass() {
		return featEClass;
	}

	public EStructuralFeature getFeat() {
		return feat;
	}

	public List<EStructuralFeature> getDerivedFeatureComponents() {
		return derivedFeatureComponents;
	}

	public String getDerivedFeatureName() {
		return derivedFeatureName;
	}

	public Function<EObject, Object> getDerivedFeatureComputationAlgorithm() {
		return derivedFeatureComputationAlgorithm;
	}
}
