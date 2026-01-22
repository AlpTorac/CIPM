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

import cipm.consistency.fluentapi.gen.FluentAPIRootAPIGenerator;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelFeatureFilter;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelPackageProvider;

public class FluentAPIBuilder {
	private static final String fluentAPIEcoreModelDirName = "initModel";
	private static final String fluentAPIModelName = "initialiserModels";

	private static final String fluentAPIEcoreModelFileName = fluentAPIModelName + ".ecore";
	private static final String fluentAPIGenModelFileName = fluentAPIModelName + ".genmodel";

	private static final File fluentAPIEcoreModelFile = new File(fluentAPIEcoreModelDirName).toPath()
			.resolve(fluentAPIEcoreModelFileName).toAbsolutePath().toFile();
	private static final Path fluentAPIEcoreModelFilePath = fluentAPIEcoreModelFile.toPath();

	private static final File fluentAPIGenModelFile = new File(fluentAPIEcoreModelDirName).toPath()
			.resolve(fluentAPIGenModelFileName).toAbsolutePath().toFile();
	private static final Path fluentAPIGenModelFilePath = fluentAPIGenModelFile.toPath();

	@Test
	public void generateModelFiles() {
		if (fluentAPIEcoreModelFile.exists()) {
			for (var file : fluentAPIEcoreModelFile.listFiles()) {
				file.delete();
			}
			fluentAPIEcoreModelFile.delete();
		}

		var metamodelPackageProvider = new FluentAPIJavaMetamodelPackageProvider();
		var featureFilter = new FluentAPIJavaMetamodelFeatureFilter();

		var ecoreResSet = new ResourceSetImpl();
		var ecoreRes = ecoreResSet.createResource(URI.createFileURI(fluentAPIEcoreModelFilePath.toString()));

		ecoreRes.getContents().add(new FluentAPIRootAPIGenerator()
				.generateRootAPIPackages(metamodelPackageProvider, featureFilter).get(0));

		var genModelResSet = new ResourceSetImpl();
		var genModelRes = genModelResSet.createResource(URI.createFileURI(fluentAPIGenModelFilePath.toString()));
		var genModel = GenModelFactory.eINSTANCE.createGenModel();

		genModel.setModelDirectory(
				"/cipm.consistency.fluentapi/src-gen/" + metamodelPackageProvider.getTargetMetamodelName());
		genModel.setModelPluginID("cipm.consistency.fluentapi");
		genModel.setImporterID("org.eclipse.emf.importer.ecore");
		genModel.setRootExtendsClass("org.eclipse.emf.ecore.impl.MinimalEObjectImpl$Container");
		genModel.setOperationReflection(true);
		genModel.setImportOrganizing(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		genModel.setCopyrightFields(false);
		genModel.setModelName(fluentAPIModelName);
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
