package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.FluentAPIRootPackageGenerator;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelFeatureFilter;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelPackageProvider;

public class FluentAPIBuilder {
	private static final String initModelDirName = "initModel";

	private static final String modelName = "initialiserModels";
	private static final File initModelFile = new File(initModelDirName).getAbsoluteFile();
	private static final Path ecoreFilePath = initModelFile.toPath().resolve(modelName + ".ecore");
	private static final Path genModelFilePath = initModelFile.toPath().resolve(modelName + ".genmodel");

	@Test
	public void generateModelFiles() {
		if (initModelFile.exists()) {
			for (var file : initModelFile.listFiles()) {
				file.delete();
			}
			initModelFile.delete();
		}

		var metamodelPackageProvider = new FluentAPIJavaMetamodelPackageProvider();
		var featureFilter = new FluentAPIJavaMetamodelFeatureFilter();

		var ecoreResSet = new ResourceSetImpl();
		var ecoreRes = ecoreResSet.createResource(URI.createFileURI(ecoreFilePath.toString()));

		ecoreRes.getContents().add(
				new FluentAPIRootPackageBuilder().buildRootPackage(metamodelPackageProvider, featureFilter).get(0));

		var genModelResSet = new ResourceSetImpl();
		var genModelRes = genModelResSet.createResource(URI.createFileURI(genModelFilePath.toString()));
		var genModel = GenModelFactory.eINSTANCE.createGenModel();

		/*
		 * modelDirectory="/cipm.consistency.fluentapi/src"
		 * 
		 * modelPluginID="cipm.consistency.fluentapi"
		 * 
		 * modelName="InitialiserModels"
		 * 
		 * rootExtendsClass="org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container"
		 * 
		 * importerID="org.eclipse.emf.importer.ecore"
		 * 
		 * complianceLevel="5.0"
		 * 
		 * copyrightFields="false"
		 * 
		 * usedGenPackages=
		 * "../../org.emftext.language.java/metamodel/java.genmodel#//java"
		 * 
		 * operationReflection="true"
		 * 
		 * importOrganizing="true">
		 * 
		 * <foreignModel>initialiserModels.ecore</foreignModel>
		 */

		genModel.setModelDirectory("/cipm.consistency.fluentapi/src");
		genModel.setModelPluginID("cipm.consistency.fluentapi");
		genModel.setImporterID("org.eclipse.emf.importer.ecore");
		genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
		genModel.setOperationReflection(true);
		genModel.setImportOrganizing(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		genModel.setCopyrightFields(false);
		genModel.setModelName(modelName);
		genModel.getForeignModel().add(ecoreRes.getURI().lastSegment());

		var javaGenModel = metamodelPackageProvider.getTargetMetamodelGenModels().get(0);
		genModel.setMainGenModel(javaGenModel);
		genModel.getUsedGenPackages().addAll(javaGenModel.getGenPackages());

		genModel.initialize(Collections.singleton((EPackage) ecoreRes.getContents().get(0)));

		genModel.reconcile();
		genModelRes.getContents().add(genModel);
		try {
			ecoreRes.save(null);
			genModelRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}
}
