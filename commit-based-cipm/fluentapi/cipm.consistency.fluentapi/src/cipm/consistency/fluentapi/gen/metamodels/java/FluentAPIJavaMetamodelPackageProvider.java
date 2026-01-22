package cipm.consistency.fluentapi.gen.metamodels.java;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
	private static final String javaMetamodelName = "java";
	private static final URI javaMetamodelGenModelURI = URI
			.createURI("platform:/plugin/org.emftext.language.java/metamodel/java.genmodel");

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		var genModelResSet = new ResourceSetImpl();
		var genModelRes = genModelResSet.getResource(javaMetamodelGenModelURI, true);
		return List.of((GenModel) genModelRes.getContents().get(0));
	}

	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(JavaPackage.eINSTANCE);
	}

	@Override
	public String getTargetMetamodelName() {
		return javaMetamodelName;
	}
}
