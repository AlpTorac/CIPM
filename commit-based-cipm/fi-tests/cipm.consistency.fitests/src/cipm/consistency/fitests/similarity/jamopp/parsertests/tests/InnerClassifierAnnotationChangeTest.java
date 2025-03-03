package cipm.consistency.fitests.similarity.jamopp.parsertests.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierAnnotationChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "annotationChange");

	@Override
	protected Path getModelsDirSubpath() {
		return modelsDirSubpath;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * The order of annotations does not matter in similarity checking.
	 */
	@Override
	public Boolean getExpectedSimilarityResultForModelComparison(Resource lhs, Path lhsSourceFilePath, Resource rhs,
			Path rhsSourceFilePath) {
		if (super.getExpectedSimilarityResultForModelComparison(lhs, lhsSourceFilePath, rhs, rhsSourceFilePath)) {
			return true;
		}
		return this.contentwiseSimilar(lhs, rhs);
	}
}
