package cipm.consistency.fluentapi.gen.java;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return JavaPackageUtil.getAllSubPackages();
	}

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return List.copyOf(JavaPackageUtil.getAllConcreteEClasses());
	}
}
