package cipm.consistency.similarity.features;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

public class OriginalTargetFeature extends TargetFeature {
	private final EStructuralFeature feat;

	public OriginalTargetFeature(EClass featEClass, EStructuralFeature feat) {
		super(featEClass);
		this.feat = feat;
	}

	public boolean isDerivedFeature() {
		return false;
	}

	public EStructuralFeature getFeat() {
		return feat;
	}

	@Override
	public String getFeatName() {
		return getFeat().getName();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof OriginalTargetFeature))
			return false;
		var castedO = (OriginalTargetFeature) obj;

		return this.feat.equals(castedO.feat) && this.getFeatEClass().equals(castedO.getFeatEClass());
	}

	@Override
	public boolean isFeatureRelevant(EClass eCls, EStructuralFeature feat) {
		return this.getFeatEClass().equals(eCls) && this.getFeat().equals(feat);
	}

	@Override
	public Class<?> getFeatType() {
		return getFeat().getEType().getInstanceClass();
	}
}
