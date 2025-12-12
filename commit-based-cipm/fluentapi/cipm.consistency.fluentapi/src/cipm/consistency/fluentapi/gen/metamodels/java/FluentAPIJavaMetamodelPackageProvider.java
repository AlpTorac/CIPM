package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(JavaPackage.eINSTANCE);
	}
}
