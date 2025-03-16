package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierInnerPositionChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "innerPositionChange");

	@Override
	protected Path getModelsDirSubpath() {
		return modelsDirSubpath;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Inner classifiers are considered members, whose position within their
	 * containing classifier does not matter.
	 */
	@Override
	protected boolean doesContentOrderMatter() {
		return false;
	}
}
