package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fluentapi.gen.java.FluentAPIJavaMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.java.FluentAPIJavaMetamodelPackageProvider;

public class FluentAPIBuilder {
	private static final String initModelDirName = "initModel";
	private static final File initModelFile = new File(initModelDirName).getAbsoluteFile();
	private static final Path ecoreFilePath = initModelFile.toPath().resolve("initialiserModels.ecore");

	@Test
	public void generateModelFiles() {
		if (initModelFile.exists()) {
			for (var file : initModelFile.listFiles()) {
				file.delete();
			}
			initModelFile.delete();
		}

		var resSet = new ResourceSetImpl();
		var res = resSet.createResource(URI.createFileURI(ecoreFilePath.toString()));

		res.getContents()
				.add(new FluentAPIRootPackageBuilder().buildRootPackage(new FluentAPIJavaMetamodelPackageProvider(),
						new FluentAPIJavaMetamodelFeatureFilter()).get(0));
		try {
			res.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}
}
