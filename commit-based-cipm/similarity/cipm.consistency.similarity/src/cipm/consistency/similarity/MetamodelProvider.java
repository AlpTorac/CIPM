package cipm.consistency.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;

import cipm.consistency.similarity.features.DerivedTargetFeature;

public abstract class MetamodelProvider {
	public abstract List<EPackage> getTargetMetamodelPackages();

	public abstract List<EClass> getTargetMetamodelEClasses();

	public abstract List<EStructuralFeature> getTargetMetamodelFeatures();

	public abstract List<DerivedTargetFeature> getTargetMetamodelDerivedFeatures();
}
