package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FieldStackingTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("member", "field", "stackAndSplit");

	@Override
	protected Path getModelSourceFileDirSubpath() {
		return modelsDirSubpath;
	}
}
