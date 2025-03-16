package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPComplexParserSimilarityTest;

public class GenericTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("generic");

	@Override
	protected Path getModelsDirSubpath() {
		return modelsDirSubpath;
	}
}
