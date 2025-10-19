package cipm.consistency.fluentapi.gen;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

import cipm.consistency.fluentapi.gen.java.JavaPackageUtil;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return JavaPackageUtil.getAllSubPackages();
	}
}
