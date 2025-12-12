package cipm.consistency.fluentapi.gen;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import cipm.consistency.fluentapi.gen.metamodels.MetamodelUtil;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;

public abstract class FluentAPITargetMetamodelPackageProvider {
	public List<EClass> getAllTargetMetamodelConcreteEClasses() {
		return getTargetMetamodelTopLevelPackages().stream().map(MetamodelUtil::getAllConcreteEClasses)
				.flatMap(Collection::stream).collect(Collectors.toUnmodifiableList());
	}

	public List<EPackage> getAllTargetMetamodelSubPackages() {
		return getTargetMetamodelTopLevelPackages().stream().map(MetamodelUtil::getAllSubPackages).flatMap(List::stream)
				.collect(Collectors.toUnmodifiableList());
	}

	public List<GenModel> getTargetMetamodelGenModels() {
		var genModelResSet = new ResourceSetImpl();
		var genModelRes = genModelResSet.getResource(URI.createURI("http://www.emftext.org/java"), true);
		var genModel = GenModelFactory.eINSTANCE.createGenModel();
		genModel.setContainmentProxies(true);
		genModel.setMinimalReflectiveMethods(true);
		genModel.setModelDirectory("/org.emftext.language.java/src-gen");
		genModel.setModelPluginID("org.emftext.language.java");
		genModel.setOperationReflection(false);
		genModel.setSuppressGenModelAnnotations(true);
		genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl");
		genModel.setRootExtendsInterface("org.eclipse.emf.ecore.EObject");
		genModel.setModelName("Java");
		genModel.initialize(genModelRes.getContents().stream().filter((c) -> c instanceof EPackage)
				.map((c) -> (EPackage) c).collect(Collectors.toList()));
		genModel.reconcile();
		genModelRes.getContents().add(genModel);
		return List.of(genModel);
	}

	public List<EPackage> getAllTargetMetamodelPackages() {
		var list = new ArrayList<EPackage>();
		list.addAll(getTargetMetamodelTopLevelPackages());
		list.addAll(getAllTargetMetamodelSubPackages());
		return list;
	}

	public abstract List<EPackage> getTargetMetamodelTopLevelPackages();
}
