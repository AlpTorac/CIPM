package cipm.consistency.fluentapi.gen.metamodels.pcm;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIPcmRepositoryMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureChangeable(feat)
				&& !feat.getEContainingClass().getInstanceClass().isAssignableFrom(EObject.class);
	}
}
