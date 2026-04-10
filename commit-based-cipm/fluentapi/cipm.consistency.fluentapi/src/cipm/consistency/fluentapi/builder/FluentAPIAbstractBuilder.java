package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.nio.file.Path;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public abstract class FluentAPIAbstractBuilder {
	private static final String commonEcoreModelDirName = "initModel";
	private static final String commonEcoreModelFileName = "initialiserModels.ecore";

	/**
	 * @return The name of the directory (only the name of the inner-most directory,
	 *         not the path to it), where the ecore file will be saved.
	 */
	protected String getEcoreModelDirName() {
		return commonEcoreModelDirName;
	}

	/**
	 * @return The name of the ecore file (only the name of the ecore file, not the
	 *         path to it)
	 */
	protected String getEcoreModelFileName() {
		return commonEcoreModelFileName;
	}

	/**
	 * @return The absolute path to the ecore model file
	 */
	protected Path getEcoreModelFilePath() {
		return new File(getEcoreModelDirName()).getAbsoluteFile().toPath()
				.resolve(getTargetMetamodelPackageProvider().getTargetMetamodelName()).resolve(getEcoreModelFileName());
	}

	/**
	 * Cleans up the potential previously created model files for this builder
	 * instance (does not delete model files created for other metamodels, just this
	 * metamodel)
	 */
	protected void cleanPreviousModelFiles() {
		var fluentAPIEcoreModelFile = getEcoreModelFilePath().toFile();
		if (fluentAPIEcoreModelFile.exists()) {
			for (var file : fluentAPIEcoreModelFile.listFiles()) {
				file.delete();
			}
			fluentAPIEcoreModelFile.delete();
		}
	}

	protected abstract FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider();

	protected abstract FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter();
}
