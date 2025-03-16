package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;

public abstract class AbstractJaMoPPComplexParserSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final String complexModelImplDirName = "complex-testmodels";

	@Override
	protected Path getRootDirPath() {
		return super.getRootDirPath().resolve(complexModelImplDirName).resolve(this.getModelsDirSubpath());
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
