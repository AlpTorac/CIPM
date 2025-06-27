package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

public class LocalVariableTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("localVariable");

	@Override
	protected Path getModelSourceFileDirSubpath() {
		return modelsDirSubpath;
	}
}