package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.FluentAPIConstants;
import cipm.consistency.fluentapi.gen.FluentAPIGenerationUtil;
import cipm.consistency.fluentapi.gen.FluentAPIRootAPIGenerator;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.java.FluentAPIJavaMetamodelPackageProvider;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelFeatureFilter;
//import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmRepositoryMetamodelPackageProvider;

public class FluentAPIBuilder {
	private static final String fluentAPIEcoreModelDirName = "initModel";
	private static final File fluentAPIEcoreModelFile = new File(fluentAPIEcoreModelDirName).getAbsoluteFile();
	private static final Path fluentAPIEcoreModelFilePath = fluentAPIEcoreModelFile.toPath()
			.resolve("initialiserModels.ecore");

	@Test
	public void generateModelFiles() {
		if (fluentAPIEcoreModelFile.exists()) {
			for (var file : fluentAPIEcoreModelFile.listFiles()) {
				file.delete();
			}
			fluentAPIEcoreModelFile.delete();
		}

		// TODO Generate API in "src-gen" folder instead of "src" folder

		// TODO Incorporate metamodel name to package and class names to allow
		// generating multiple APIs

		// TODO Automate GenModel generation

		// TODO Fully automate API generation

		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(fluentAPIEcoreModelFilePath.toString()));

		var arrayTypesPac = EcoreFactory.eINSTANCE.createEPackage();
		arrayTypesPac.setName("arrayTypes");
		arrayTypesPac.setNsPrefix("arrayTypes");
		arrayTypesPac
				.setNsURI(FluentAPIConstants.getFluentAPIRootPackageURI().appendSegment("arrayTypes").toString());

		// C:\Users\atora\CIPM2\commit-based-cipm\fluentapi\cipm.consistency.fluentapi\src\arrayTypes
		
//		"file:/C:/Users/atora/CIPM2/commit-based-cipm/fluentapi/cipm.consistency.fluentapi/src/cipm/consistency/fluentapi/api/arrayTypes"

		res.getContents().add(arrayTypesPac);

		FluentAPIGenerationUtil.setEcoreRes(res);
		FluentAPIGenerationUtil.setSyntheticArrayTypePackage(arrayTypesPac);

		res.getContents().add(new FluentAPIRootAPIGenerator().generateRootAPIPackages(
				new FluentAPIJavaMetamodelPackageProvider(), new FluentAPIJavaMetamodelFeatureFilter()).get(0));
		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}
}
