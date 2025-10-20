package cipm.consistency.fluentapi.gen.java;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return JavaPackageUtil.getAllSubPackages();
	}
}
