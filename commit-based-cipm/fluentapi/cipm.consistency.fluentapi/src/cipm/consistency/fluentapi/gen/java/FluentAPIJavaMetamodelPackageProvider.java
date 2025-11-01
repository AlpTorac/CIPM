package cipm.consistency.fluentapi.gen.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public class FluentAPIJavaMetamodelPackageProvider extends FluentAPITargetMetamodelPackageProvider {
//	private static final Path absPathToImportedJavaGenModel = Paths
//			.get("org.emftext.language.java", "metamodel", "java.genmodel").toAbsolutePath();

	@Override
	public List<EPackage> getTargetMetamodelTopLevelPackages() {
		return List.of(JavaPackage.eINSTANCE);
	}

	@Override
	public List<EPackage> getAllTargetMetamodelSubPackages() {
		return JavaPackageUtil.getAllSubPackages();
	}

	@Override
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return List.copyOf(JavaPackageUtil.getAllConcreteEClasses());
	}

	@Override
	public List<GenModel> getTargetMetamodelGenModels() {
		var genModelResSet = new ResourceSetImpl();
		var genModelRes = genModelResSet.createResource(URI.createFileURI(new File("").getAbsolutePath()));
		var genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModel.initialize(getTargetMetamodelTopLevelPackages());
		genModel.reconcile();
		genModelRes.getContents().add(genModel);
		return List.of(genModel);
	}

	@Override
	public List<EPackage> getAllTargetMetamodelPackages() {
		var list = new ArrayList<EPackage>();
		list.addAll(getTargetMetamodelTopLevelPackages());
		list.addAll(getAllTargetMetamodelSubPackages());
		return list;
	}
}
