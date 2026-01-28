package cipm.consistency.fluentapi.gen;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
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

	/**
	 * Not declared as static, because the underlying metamodel could introduce
	 * constraints regarding this. This is a default implementation.
	 */
	public boolean isFeatureChangeable(EStructuralFeature feat) {
		return feat.isChangeable() && !feat.isDerived();
	}

	/**
	 * Not declared as static, because the underlying metamodel could introduce
	 * constraints regarding this. This is a default implementation.
	 */
	public boolean canShareFeatureWithContainer(FluentAPITargetMetamodelPackageProvider metamodelProvider,
			EClass elemToInit, EStructuralFeature feat) {
		// Containment EReferences are not eligible here, because their contents would
		// get shifted upon using the same value for another EObject
		if (feat instanceof EReference && ((EReference) feat).isContainment())
			return false;

		// Ensure that elemToInit instances have the chance of having a container
		// that supports feat
		var allEClasses = metamodelProvider.getAllTargetMetamodelConcreteEClasses();
		return allEClasses.stream().anyMatch((eCls) -> eCls.getEAllReferences().stream().anyMatch((ref) -> {
			var refType = ref.getEType();
			return isContainmentReferenceFor(elemToInit, ref)
					&& ((EClass) refType).getEAllStructuralFeatures().contains(feat);
		}));
	}

	/**
	 * Not declared as static, because the underlying metamodel could introduce
	 * constraints regarding this. This is a default implementation.
	 */
	public boolean isContainmentReferenceFor(EClass elemToInit, EStructuralFeature potentialContainmentFeat) {
		var refType = potentialContainmentFeat.getEType();
		var refTypeCls = refType.getInstanceClass();
		return refType instanceof EClass && refTypeCls.isAssignableFrom(elemToInit.getInstanceClass());
	}
}
