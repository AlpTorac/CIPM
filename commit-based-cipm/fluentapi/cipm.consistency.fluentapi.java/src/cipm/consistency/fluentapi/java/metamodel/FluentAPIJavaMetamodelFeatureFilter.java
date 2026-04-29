package cipm.consistency.fluentapi.java.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.CommonsPackage;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;

public class FluentAPIJavaMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureChangeable(feat)
				&& !feat.getEContainingClass().getName().equals(CommonsPackage.Literals.COMMENTABLE.getName());
//				!CommonsPackage.Literals.COMMENTABLE.isSuperTypeOf(feat.getEContainingClass())
//				!feat.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class)
	}
}
