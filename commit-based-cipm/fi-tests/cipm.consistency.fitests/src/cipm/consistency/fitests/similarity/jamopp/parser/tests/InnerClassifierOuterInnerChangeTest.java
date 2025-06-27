package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InnerClassifierOuterInnerChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "outerInnerChange");

	@Override
	protected Path getModelSourceFileDirSubpath() {
		return modelsDirSubpath;
	}
}
