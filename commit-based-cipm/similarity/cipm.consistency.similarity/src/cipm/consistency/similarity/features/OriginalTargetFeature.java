package cipm.consistency.similarity.features;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
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

	public Object computeFeatureValue(EObject obj) {
		if (obj.eClass() != getFeatEClass())
			return null;

		return obj.eGet(feat);
	}

	public EStructuralFeature getFeat() {
		return feat;
	}

	@Override
	public String getFeatName() {
		return getFeat().getName();
	}

	@Override
	public boolean isManyFeature() {
		return getFeat().isMany();
	}
}
