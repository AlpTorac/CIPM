package cipm.consistency.fluentapi.gen.java;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.Commentable;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIJavaMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return this.isFeatureEligible(feat)
				&& !feat.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class);
	}
}
