package cipm.consistency.fitests.similarity.jamopp.parsertests.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierNestedInnerTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "nestedInner");

	@Override
	protected Path getModelsDirSubpath() {
		return modelsDirSubpath;
	}
}
