package cipm.consistency.fluentapi.java.metamodel;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emftext.language.java.commons.CommonsPackage;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;

/**
 * An implementation of {@link FluentAPITargetMetamodelFeatureFilter} for
 * JaMoPP.
 * <p>
 * <p>
 * Excludes the features ( {@link EStructuralFeature} ) that are present in
 * {@link Commentable} and its super-types.
 * 
 * @author Alp Torac Genc
 */
public class FluentAPIJavaMetamodelFeatureFilter extends FluentAPITargetMetamodelFeatureFilter {
	@Override
	public boolean isFeatureEligible(EClass holderOfFeat, EStructuralFeature feat) {
		return isFeatureChangeable(feat)
				&& !feat.getEContainingClass().getName().equals(CommonsPackage.Literals.COMMENTABLE.getName());
//				!CommonsPackage.Literals.COMMENTABLE.isSuperTypeOf(feat.getEContainingClass())
//				!feat.getEContainingClass().getInstanceClass().isAssignableFrom(Commentable.class)
	}
}
