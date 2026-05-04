package cipm.consistency.fluentapi.builder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.osgi.framework.FrameworkUtil;

import cipm.consistency.fluentapi.gen.FluentAPIGenerationContext;
import cipm.consistency.fluentapi.gen.ModelConstants;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelFeatureFilter;
import cipm.consistency.fluentapi.metamodel.FluentAPITargetMetamodelPackageProvider;

public abstract class FluentAPIAbstractBuilder {
	/**
	 * The suffix all generated fluent api model files (i.e. the ecore and genmodel
	 * files) should have
	 */
	private static final String commonModelSuffix = "fluentapi";
	/**
	 * The name of the directory, where the fluent api model files will be generated
	 */
	private static final String commonModelDirName = "metamodel";
	/**
	 * The name of the ecore file associated with the fluent api
	 */
	private static final String commonEcoreModelFileName = commonModelSuffix + ".ecore";
	/**
	 * The name of the genmodel file associated with the fluent api
	 */
	private static final String commonGenModelFileName = commonModelSuffix + ".genmodel";
	/**
	 * The name of the directory, where the fluent api itself will be generated
	 * (i.e. the classes that can be used to construct models of the targeted
	 * metamodel)
	 */
	private static final String modelGenerationTargetDirName = "src-gen";

	/**
	 * Generates the fluent API model files (i.e. the ecore and genmodel files).
	 * <p>
	 * Can be overridden in concrete implementors to change how the model files are
	 * generated. If overridden, the overriding method should have the {@code @Test}
	 * annotation for JUnit to detect it as a test case. Otherwise, the overriding
	 * method will not be recognised as a test method and no model files will be
	 * generated.
	 */
	@Test
	public void generateModelFiles() {
		cleanPreviousModelFiles();

		var context = new FluentAPIGenerationContext();
		context.setTargetMetamodelPackageProvider(getTargetMetamodelPackageProvider());
		context.setTargetMetamodelFeatureFilter(getTargetMetamodelFeatureFilter());
		context.setBasePackageName(
				ModelConstants.BASE_PACKAGE_NAME.getFor(getTargetMetamodelPackageProvider().getTargetMetamodelName()));

		var modelResSet = new ResourceSetImpl();
		var ecoreRes = modelResSet.createResource(URI.createFileURI(getEcoreModelFilePath().toString()));
		var genModelRes = modelResSet.createResource(URI.createFileURI(getGenModelFilePath().toString()));

		generateEcoreModel(ecoreRes, context);
		generateGenModel(genModelRes, ecoreRes, context);

		try {
			ecoreRes.save(null);
			genModelRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
			Assertions.fail(e);
		}
	}

	/**
	 * @return The value of the "model name" property of the genmodel of the fluent
	 *         api.
	 */
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
	 * @return The absolute path to the ecore model file associated with the fluent
	 *         api
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

	/**
	 * @return The value of the "compliance level" property of the genmodel of
	 *         fluent api.
	 */
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

	/**
	 * @return The value of the "model plugin ID" property of the genmodel of fluent
	 *         api.
	 */
	protected String getModelPluginID() {
		return ModelConstants.BASE_PACKAGE_NAME.getFor(getTargetMetamodelPackageProvider().getTargetMetamodelName());
	}

	/**
	 * Returns The path, at which the fluent API will be generated.
	 * <p>
	 * Can be overridden in concrete implementors.
	 * 
	 * @return The value of the "model directory" property of the genmodel of fluent
	 *         api as Path.
	 */
	protected Path getModelDirectoryPath() {
		return Path.of(getCurrentPluginName(), modelGenerationTargetDirName);
	}

	/**
	 * Meant to be used by {@link #getModelDirectoryPath()}.
	 * 
	 * @return The name of the current plug-in.
	 */
	private String getCurrentPluginName() {
		var bundle = FrameworkUtil.getBundle(getClass());
		var name = bundle.getSymbolicName();
		return name;
	}

	protected abstract FluentAPITargetMetamodelPackageProvider getTargetMetamodelPackageProvider();

	protected abstract FluentAPITargetMetamodelFeatureFilter getTargetMetamodelFeatureFilter();

	protected abstract GenModel generateGenModel(Resource genModelRes, Resource ecoreRes,
			FluentAPIGenerationContext context);

	protected abstract void generateEcoreModel(Resource ecoreRes, FluentAPIGenerationContext context);
}
