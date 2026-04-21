package cipm.consistency.fluentapi.gen.metamodels.pcm;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIPcmMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureChangeable(feat)
				&& !feat.getEContainingClass().getName().equals(EcorePackage.Literals.EOBJECT.getName());
//				!feat.getEContainingClass().getInstanceClass().isAssignableFrom(EObject.class)
	}
}
