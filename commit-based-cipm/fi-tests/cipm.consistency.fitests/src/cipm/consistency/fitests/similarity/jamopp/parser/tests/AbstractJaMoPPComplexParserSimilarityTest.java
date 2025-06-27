package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTest;
import cipm.consistency.fitests.similarity.jamopp.parser.CombinationTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.IJaMoPPParserTestGenerationStrategy;

/**
 * Extension of {@link AbstractJaMoPPParserSimilarityTest} for complex models.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractJaMoPPComplexParserSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The relative path to the complex models
	 */
	private static final Path complexModelImplDirPath = Path.of("testmodels", "complex-models");

	@Override
	protected Path getModelSourceFileRootDirPath() {
		return super.getModelSourceFileRootDirPath().resolve(complexModelImplDirPath)
				.resolve(this.getModelSourceFileDirSubpath());
	}

	@Override
	protected boolean isModelSourceFileDirectoryName(String s) {
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
	 * contains the model source files that will be used by the test.
	 * 
	 * @return The path to the directory, from which onward model directories will
	 *         be searched for this particular test.
	 */
	protected abstract Path getModelSourceFileDirSubpath();
}
