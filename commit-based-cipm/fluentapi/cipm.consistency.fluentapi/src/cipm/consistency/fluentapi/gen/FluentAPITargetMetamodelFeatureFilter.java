package cipm.consistency.fluentapi.gen;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class FluentAPITargetMetamodelFeatureFilter {
	public abstract boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat);

	public boolean hasModifiableFeatures(EClass eObjEClass) {
		return eObjEClass.getEAllStructuralFeatures().stream().anyMatch((f) -> this.isFeatureEligible(eObjEClass, f));
	}

	public boolean isFeatureEligible(EObject eObj, EStructuralFeature feat) {
		return this.isFeatureEligible(eObj.eClass(), feat);
	}

	public static boolean isFeatureActuallyChangeable(EStructuralFeature feat) {
		return feat.isChangeable() && !feat.isDerived();
	}
}
