package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.nio.file.Path;

import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.gen.FluentAPITargetMetamodelPackageProvider;

public abstract class FluentAPIAbstractBuilder {
	private static final String modelName = "initialiserModels";
	private static final String commonModelDirName = "initModel";
	private static final String commonEcoreModelFileName = modelName + ".ecore";
	private static final String commonGenModelFileName = modelName + ".genmodel";

	protected String getModelName() {
		return modelName;
	}

	/**
	 * @return The absolute path to the .genmodel file associated with the fluent
	 *         API model
	 */
	protected Path getGenModelFilePath() {
		return new File(getModelDirName()).getAbsoluteFile().toPath()
				.resolve(getTargetMetamodelPackageProvider().getTargetMetamodelName()).resolve(getGenModelFileName());
	}

	/**
	 * @return The name of the ".genmodel" file associated with the fluent API model
	 */
	protected String getGenModelFileName() {
		return commonGenModelFileName;
	}

	/**
	 * @return The name of the directory (only the name of the inner-most directory,
	 *         not the path to it), where the .ecore and .genmodel file will be
	 *         saved.
	 */
	protected String getModelDirName() {
		return commonModelDirName;
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
		return new File(getModelDirName()).getAbsoluteFile().toPath()
				.resolve(getTargetMetamodelPackageProvider().getTargetMetamodelName()).resolve(getEcoreModelFileName());
	}

	/**
	 * Cleans up the potential previously created model files for this builder
	 * instance (does not delete model files created for other metamodels, just this
	 * metamodel)
	 */
	protected void cleanPreviousModelFiles() {
		var fluentAPIEcoreModelFile = getEcoreModelFilePath().toFile();
		if (fluentAPIEcoreModelFile.exists() && fluentAPIEcoreModelFile.listFiles() != null) {
			for (var file : fluentAPIEcoreModelFile.listFiles()) {
				file.delete();
			}
			fluentAPIEcoreModelFile.delete();
		}
	}

	protected abstract FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider();

	protected abstract FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter();
}
