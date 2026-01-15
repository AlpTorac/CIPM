package cipm.consistency.fluentapi.gen;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public abstract class FluentAPITargetMetamodelFeatureFilter {
	public abstract boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat);

	public List<EStructuralFeature> getModifiableFeatures(EClass eObjEClass) {
		return eObjEClass.getEAllStructuralFeatures().stream().filter((f) -> this.isFeatureEligible(eObjEClass, f))
				.collect(Collectors.toList());
	}

	public long getModifiableFeatureCount(EClass eObjEClass) {
		return eObjEClass.getEAllStructuralFeatures().stream().filter((f) -> this.isFeatureEligible(eObjEClass, f))
				.count();
	}

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
