package cipm.consistency.fitests.similarity.jamopp.parsertests.innerclassifier;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierInnerPositionChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "innerPositionChange");

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
	 * Inner classifiers are considered members, whose position within their
	 * containing classifier does not matter.
	 */
	@Override
	public Boolean getExpectedSimilarityResultForModelComparison(Resource lhs, Path lhsSourceFilePath, Resource rhs,
			Path rhsSourceFilePath) {
		return true;
	}
}
