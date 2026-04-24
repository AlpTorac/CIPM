package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;

import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;

public abstract class FluentAPIAbstractBuilder {
	private static final String commonModelSuffix = "fluentapi";
	private static final String commonModelDirName = "metamodel";
	private static final String commonEcoreModelFileName = commonModelSuffix + ".ecore";
	private static final String commonGenModelFileName = commonModelSuffix + ".genmodel";

	protected String getCommonModelSuffix() {
		return commonModelSuffix;
	}

	protected String getModelName() {
		return getTargetMetamodelPackageProvider().getTargetMetamodelName() + "-" + commonModelSuffix;
	}

	/**
	 * @return The absolute path to the .genmodel file associated with the fluent
	 *         API model
	 */
	protected Path getGenModelFilePath() {
		return new File(getModelDirName()).getAbsoluteFile().toPath().resolve(getGenModelFileName());
	}

	/**
	 * @return The name of the ".genmodel" file associated with the fluent API model
	 */
	protected String getGenModelFileName() {
		return getTargetMetamodelPackageProvider().getTargetMetamodelName() + "-" + commonGenModelFileName;
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
		return getTargetMetamodelPackageProvider().getTargetMetamodelName() + "-" + commonEcoreModelFileName;
	}

	/**
	 * @return The absolute path to the ecore model file
	 */
	protected Path getEcoreModelFilePath() {
		return new File(getModelDirName()).getAbsoluteFile().toPath().resolve(getEcoreModelFileName());
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

	protected static GenJDKLevel getJDKVersion() {
		var runtimeVer = Runtime.version().version().get(0);
		GenJDKLevel lvl = null;
		for (var ver : GenJDKLevel.values()) {
			if (ver.getLiteral().startsWith(String.valueOf(runtimeVer.doubleValue()))) {
				lvl = ver;
				break;
			}
		}
		return lvl;
	}

	protected abstract FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider();

	protected abstract FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter();
}
