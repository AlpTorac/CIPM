package cipm.consistency.fluentapi.metamodel;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * 
 * TODO Add proper commentary
 * 
 * Note: Do not use the original metamodel packages while type-checking or
 * filtering, because {@link FluentAPITargetMetamodelPackageProvider} does not
 * use the original metamodel packages. Attempting to use
 * {@code originalECls.isSuperTypeOf(givenECls)} or vice versa will always
 * result in false, due to the original EClass and the given EClass being in
 * different models entirely. Instead, use their EAttributes for type-checking
 * (such as their name); excluding {@code eCls.getInstanceClass()} and related
 * methods, since they are not guaranteed to exist in parsed models.
 * 
 * @author Alp Torac Genc
 */
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
	public boolean isContainmentReferenceFor(EClass elemToInit, EStructuralFeature potentialContainmentFeat) {
		var refType = potentialContainmentFeat.getEType();
		var refTypeCls = refType.getInstanceClass();
		return refType instanceof EClass && refTypeCls.isAssignableFrom(elemToInit.getInstanceClass());
	}
}
