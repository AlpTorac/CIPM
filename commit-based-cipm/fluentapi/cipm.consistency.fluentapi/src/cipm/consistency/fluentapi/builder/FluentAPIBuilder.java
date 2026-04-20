package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import cipm.consistency.fluentapi.gen.FluentAPIRootAPIGenerator;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelFeatureFilter;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelPackageProvider;

public class FluentAPIBuilder {
	private static final String fluentAPIEcoreModelDirName = "initModel";
	private static final File fluentAPIEcoreModelDirFile = new File(fluentAPIEcoreModelDirName);

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
		Bundle bundle = Platform.getBundle("org.eclipse.emf.codegen.ecore");
		try {
			bundle.start(Bundle.START_TRANSIENT);
		} catch (BundleException e1) {
			e1.printStackTrace();
			Assertions.fail(e1);
		}
		System.out.println(bundle.getState());
		System.out.println(bundle.getEntry("/templates/model/Class.javajet"));

		if (fluentAPIEcoreModelDirFile.exists()) {
			for (var file : fluentAPIEcoreModelDirFile.listFiles()) {
				file.delete();
			}
		}

		var metamodelPackageProvider = new FluentAPIJavaMetamodelPackageProvider();
		var featureFilter = new FluentAPIJavaMetamodelFeatureFilter();

		var ecoreResSet = metamodelPackageProvider.getTargetMetamodelResourceSet();
		var ecoreRes = ecoreResSet.createResource(URI.createFileURI(fluentAPIEcoreModelFilePath.toString()));

		ecoreRes.getContents().add(new FluentAPIRootAPIGenerator()
				.generateRootAPIPackages(metamodelPackageProvider, featureFilter).get(0));

		var genModelRes = ecoreResSet.createResource(URI.createFileURI(fluentAPIGenModelFilePath.toString()));
		var genModel = GenModelFactory.eINSTANCE.createGenModel();

		genModel.setModelDirectory(
				"/cipm.consistency.fluentapi/src-gen/" + metamodelPackageProvider.getTargetMetamodelName());
		genModel.setOperationReflection(true);
		genModel.setImportOrganizing(true);
		genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
		genModel.setModelName(fluentAPIModelName);
		genModel.setModelPluginID("cipm.consistency.fluentapi");
		genModel.getForeignModel().add(ecoreRes.getURI().lastSegment());

		var javaGenModel = metamodelPackageProvider.getTargetMetamodelGenModels().get(0);
		genModel.getUsedGenPackages().addAll(javaGenModel.getGenPackages());

		var initEPacs = new ArrayList<EPackage>();
		var toGen = (EPackage) ecoreRes.getContents().get(0);

		initEPacs.add(toGen);
		genModel.initialize(initEPacs);

		genModel.reconcile();

		// TODO Use Generator and GeneratorAdapterFactory to generate code afterward,
		// see the documentation of org.eclipse.emf.codegen.ecore.generator.Generator
		// for example

		genModelRes.getContents().add(genModel);

		try {
			ecoreRes.save(null);
			genModelRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}

		System.out.println("Update Classpath: " + genModel.isUpdateClasspath());
		System.out.println("Format code: " + genModel.isCodeFormatting());

		genModel.setCanGenerate(true);
		EcoreUtil.resolveAll(ecoreResSet);

		System.out.println("Generator class loader: "
				+ org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory.class
						.getClassLoader());

		// Create the generator and set the model-level input object.
		//
		Generator generator = new Generator();
		generator.getAdapterFactoryDescriptorRegistry().addDescriptor(GenModelPackage.eNS_URI,
				org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory.DESCRIPTOR);
		System.out.println("Factory: " + generator.getAdapterFactoryDescriptorRegistry());
		generator.setInput(genModel);
		System.out.println("Input set to: " + generator.getInput());

		System.out.println("Adapter creation: "
				+ org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory.DESCRIPTOR
						.createAdapterFactory());
		System.out.println("Descriptor size: "
				+ generator.getAdapterFactoryDescriptorRegistry().getDescriptors(GenModelPackage.eNS_URI).size());

		var genCls = genModel.getAllGenPackagesWithClassifiers().stream().map((p) -> p.getGenClasses())
				.flatMap(List::stream).findFirst().get();

		System.out.println("GenClass adaptation attempt: "
				+ generator.getAdapterFactoryDescriptorRegistry().getDescriptors(GenModelPackage.eNS_URI).iterator()
						.next().createAdapterFactory().adapt(genCls, GenClass.class));

		System.out.println("Class.javajet resource: "
				+ org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter.class
						.getResource("/templates/model/Class.javajet"));

//		var bundle = Platform.getBundle("org.eclipse.emf.codegen.ecore");
		System.out.println("Bundle: " + bundle);
		System.out.println("Bundle state: " + bundle.getState());
		System.out.println("Bundle entry: " + bundle.getEntry("/templates/model/Class.javajet"));

		var url = bundle.getEntry("/templates/model/Class.javajet");
		System.out.println("Class.javaJet URL: " + url);

		System.out.println("Class.javaJet URL via FileLocator: "
				+ FileLocator.find(Platform.getBundle("org.eclipse.emf.codegen.ecore"),
						new org.eclipse.core.runtime.Path("/templates/model/Class.javajet"), null));

		// Generator model code.
		//
		var d = generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
				new BasicMonitor.Printing(System.out));
		printDiagnostic(d, "----");

		for (var p : genModel.getGenPackages()) {
			printCanGenerate(p);
		}

//		try {
//			ResourcesPlugin.getWorkspace().run(monitor -> {
//				generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
//						new BasicMonitor.Printing(System.out));
//			}, null);
//		} catch (CoreException e) {
//			e.printStackTrace();
//			Assertions.fail(e);
//		}

		System.out.println("SEVERITY = " + d.getSeverity());
		System.out.println(d.getMessage());
	}

	private void printCanGenerate(GenPackage pac) {
		System.out.println("PACKAGE: " + pac.getPrefix());
		System.out.println("  generate = " + pac.canGenerate());
		System.out.println("  classes = " + pac.getGenClasses().size());

		System.out.println(String.format("Can generate PACKAGE %s: %s", pac.getBasicPackageName(), pac.canGenerate()));
		for (var p : pac.getNestedGenPackages()) {
			for (var gc : p.getGenClasses()) {
				System.out.println("CLASS: " + gc.getName());
				System.out.println("  generate = " + gc.canGenerate());
				System.out.println("  dynamic  = " + gc.isDynamic());
				System.out.println("  ecore    = " + gc.getEcoreClass());
				System.out.println("  features    = " + gc.getGenFeatures().size());
			}

			printCanGenerate(p);
		}
	}

	private void printDiagnostic(Diagnostic d, String indent) {
		System.out.println(indent + d.getMessage());
		for (Diagnostic child : d.getChildren()) {
			printDiagnostic(child, indent + "  ");
		}
	}
}
