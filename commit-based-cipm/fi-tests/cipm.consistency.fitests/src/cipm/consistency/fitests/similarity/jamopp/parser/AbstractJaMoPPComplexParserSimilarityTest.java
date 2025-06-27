package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractJaMoPPComplexParserSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final Path complexModelImplDirPath = Path.of("testmodels", "complex-models");

	@Override
	protected Path getModelSourceFileRootDirPath() {
		return super.getModelSourceFileRootDirPath().resolve(complexModelImplDirPath)
				.resolve(this.getModelSourceFileDirSubpath());
	}

	@Override
	protected boolean isModelSourceFileDirectoryName(String s) {
		// TODO Replace once a better method is found for this

		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	protected Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies() {
		var strats = new ArrayList<IJaMoPPParserTestGenerationStrategy>();
		strats.add(new CombinationTestGenerationStrategy());
		return strats;
	}

	/**
	 * Override in concrete tests with the path to the topmost directory, which
	 * contains the model files that will be used by the test.
	 * 
	 * @return The path to the directory, from which onward model directories will
	 *         be searched for this particular test.
	 */
	protected abstract Path getModelSourceFileDirSubpath();
}
