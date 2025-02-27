package cipm.consistency.fitests.similarity.jamopp.parsertests.innerclassifier;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierGenericChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "genericChange");

	@Override
	protected Path getRootDirPath() {
		return Paths.get(super.getRootDirPath().toString(), modelsDirSubpath.toString());
	}

	@Override
	protected boolean isResourceRelevant(Path sourcePath, Resource r) {
		return sourcePath.toString().contains(modelsDirSubpath.toString());
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * The order of generic parameters does not matter in similarity checking.
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
