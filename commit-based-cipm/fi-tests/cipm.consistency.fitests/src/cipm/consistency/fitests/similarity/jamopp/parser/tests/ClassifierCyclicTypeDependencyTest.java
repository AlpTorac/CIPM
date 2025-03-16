package cipm.consistency.fitests.similarity.jamopp.parser.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPComplexParserSimilarityTest;

public class ClassifierCyclicTypeDependencyTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "cyclicTypeDependency");

	@Override
	protected Path getModelsDirSubpath() {
		return modelsDirSubpath;
	}
}
