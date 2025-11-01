package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public abstract class FluentAPITargetMetamodelPackageProvider {
	public abstract List<EPackage> getTargetMetamodelTopLevelPackages();
	public abstract List<EPackage> getAllTargetMetamodelSubPackages();
	public abstract List<EPackage> getAllTargetMetamodelPackages();
	public abstract List<EClass> getAllTargetMetamodelConcreteEClasses();

	public abstract List<GenModel> getTargetMetamodelGenModels();
}
