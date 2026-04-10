package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.FluentAPIGenerator;
import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.metamodels.pcm.FluentAPIPcmMetamodelPackageProvider;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationBigNumberParameterPostProcessor;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationForEachOverloadPostProcessor;
import cipm.consistency.fluentapi.gen.postprocessor.FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor;

public class FluentPCMAPIBuilder {
	private static final String fluentAPIEcoreModelDirName = "initModel/pcmInitModel";
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

		var context = new FluentAPIGenerationContext();
		context.setTargetMetamodelPackageProvider(new FluentAPIPcmMetamodelPackageProvider());
		context.setTargetMetamodelFeatureFilter(new FluentAPIPcmMetamodelFeatureFilter());

		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(fluentAPIEcoreModelFilePath.toString()));

		new FluentAPIGenerator().generateRootAPIPackages(context);

		new FluentAPIGenerationBigNumberParameterPostProcessor(context.getAllInitEClss()).apply();

		var allEClss = new ArrayList<EClass>();
		allEClss.add(context.getFluentAPIECls());
		allEClss.add(context.getInitSuperECls());
		allEClss.addAll(context.getAllInitEClss());

		new FluentAPIGenerationForEachOverloadPostProcessor(context, allEClss).apply();
		new FluentAPIGenerationMultipleValueParameterSameMethodBodyOverloadPostProcessor(context, allEClss).apply();

		res.getContents().add(context.getRootPackage());

		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}
}
