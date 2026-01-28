package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	@Override
	public List<EPackage> getTargetMetamodelPackages() {
		return MetamodelUtil.getAllSubPackages(JavaPackage.eINSTANCE);
	}

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return List.copyOf(MetamodelUtil.getAllConcreteEClasses(JavaPackage.eINSTANCE));
	}

	@Override
	public String getTargetMetamodelName() {
		return JavaPackage.eINSTANCE.getName();
	}

	@Override
	public List<EClass> getAllTargetMetamodelEClasses() {
		return List.copyOf(MetamodelUtil.getAllEClasses(JavaPackage.eINSTANCE));
	}
}
