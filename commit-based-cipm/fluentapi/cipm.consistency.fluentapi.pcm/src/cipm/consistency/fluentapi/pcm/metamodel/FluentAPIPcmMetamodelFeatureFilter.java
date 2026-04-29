package cipm.consistency.fluentapi.pcm.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIPcmMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureChangeable(feat)
				&& !feat.getEContainingClass().getName().equals(EcorePackage.Literals.EOBJECT.getName());
//				!feat.getEContainingClass().getInstanceClass().isAssignableFrom(EObject.class)
	}
}
