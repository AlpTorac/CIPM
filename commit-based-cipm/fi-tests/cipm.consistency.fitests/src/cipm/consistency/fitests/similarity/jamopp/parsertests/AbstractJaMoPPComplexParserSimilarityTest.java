package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.Resource;

public abstract class AbstractJaMoPPComplexParserSimilarityTest extends AbstractJaMoPPParserSimilarityTestFactory {
	/**
	 * The name of the root directory of the models
	 */
	private static final String complexModelImplDirName = "complex-testmodels";

	@Override
	protected Path getRootDirPath() {
		var pathToComplexModels = Paths.get(super.getRootDirPath().toString(), complexModelImplDirName);
		return Paths.get(pathToComplexModels.toString(), this.getModelsDirSubpath().toString());
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		// TODO Replace once a better method is found for this

		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Defaults to checking whether the source path contains
	 * {@link #getModelsDirSubpath()}.
	 */
	@Override
	protected boolean isResourceRelevant(Path sourcePath, Resource r) {
		return sourcePath.toString().contains(this.getModelsDirSubpath().toString());
	}

	/**
	 * Override in concrete tests with the path to the topmost directory, which
	 * contains the model files that will be used by the test.
	 * 
	 * @return The path to the directory, from which onward model directories will
	 *         be searched for this particular test.
	 */
	protected abstract Path getModelsDirSubpath();
}
