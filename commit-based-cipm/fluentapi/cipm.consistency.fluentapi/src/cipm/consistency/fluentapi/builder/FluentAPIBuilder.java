package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.FluentAPIRootPackageGenerator;
import cipm.consistency.fluentapi.gen.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.java.FluentAPIJavaMetamodelPackageProvider;

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
		 * 
		 * modelDirectory="/cipm.consistency.fluentapi/src"
		 * modelPluginID="cipm.consistency.fluentapi"
		 * importerID="org.eclipse.emf.importer.ecore"
		 * rootExtendsClass="org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container"
		 * usedGenPackages=
		 * "../../org.emftext.language.java/metamodel/java.genmodel#//java"
		 * operationReflection="true" importOrganizing="true"
		 * 
		 */

		genModel.setModelDirectory(FluentAPIRootPackageGenerator.getRootPackageDirectoryPath().toString());
		genModel.setModelPluginID(FluentAPIRootPackageGenerator.getRootPackageName());
//		genModel.setImporterID("org.eclipse.emf.importer.ecore");
//		genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
		genModel.setOperationReflection(true);
		genModel.setImportOrganizing(true);
//		genModel.setComplianceLevel(GenJDKLevel.JDK110_LITERAL);
		genModel.setModelName(modelName);
		genModel.getForeignModel().add(ecoreRes.getURI().lastSegment());

		genModel.initialize(Collections.singleton((EPackage) ecoreRes.getContents().get(0)));
		genModel.getUsedGenPackages().addAll(metamodelPackageProvider.getTargetMetamodelGenModels().stream()
				.map((gm) -> gm.getGenPackages()).flatMap(Collection::stream).collect(Collectors.toList()));
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
