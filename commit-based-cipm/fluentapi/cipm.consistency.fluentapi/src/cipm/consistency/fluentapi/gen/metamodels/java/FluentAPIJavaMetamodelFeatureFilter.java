package cipm.consistency.fluentapi.gen.metamodels.java;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.Commentable;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIJavaMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureActuallyChangeable(feat)
				&& !(feat.getEContainingClass().getName().equals(Commentable.class.getSimpleName()));
//				&& !feat.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class);
	}
}
