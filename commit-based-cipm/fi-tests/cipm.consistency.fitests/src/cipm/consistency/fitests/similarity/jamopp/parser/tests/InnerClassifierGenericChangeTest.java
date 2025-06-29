package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InnerClassifierGenericChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "genericChange");

	@Override
	protected Path getModelSourceFileDirSubpath() {
		return modelsDirSubpath;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * The order of generic parameters does not matter in similarity checking.
	 */
	@Override
	public boolean doesContentOrderMatter() {
		return false;
	}
}
