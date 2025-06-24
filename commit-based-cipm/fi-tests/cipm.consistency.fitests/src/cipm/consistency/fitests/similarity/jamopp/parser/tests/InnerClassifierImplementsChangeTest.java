package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierImplementsChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "implementsChange");

	@Override
	protected Path getModelSourceFileDirSubpath() {
		return modelsDirSubpath;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * The order of implemented interfaces does not matter in similarity checking.
	 */
	@Override
	protected boolean doesContentOrderMatter() {
		return false;
	}
}
