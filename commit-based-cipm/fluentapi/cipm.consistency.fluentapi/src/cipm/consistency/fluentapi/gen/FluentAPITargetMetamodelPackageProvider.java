package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public abstract class FluentAPITargetMetamodelPackageProvider {
	public abstract List<EPackage> getTargetMetamodelPackages();

	public abstract String getTargetMetamodelName();

	public abstract List<EClass> getAllTargetMetamodelConcreteEClasses();
}
