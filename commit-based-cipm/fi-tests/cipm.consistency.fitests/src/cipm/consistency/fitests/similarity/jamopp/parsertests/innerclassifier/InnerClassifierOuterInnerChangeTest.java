package cipm.consistency.fitests.similarity.jamopp.parsertests.innerclassifier;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.emf.ecore.resource.Resource;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPComplexParserSimilarityTest;

public class InnerClassifierOuterInnerChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path modelsDirSubpath = Paths.get("classifier", "innerClassifier", "outerInnerChange");

	@Override
	protected Path getRootDirPath() {
		return Paths.get(super.getRootDirPath().toString(), modelsDirSubpath.toString());
	}

	@Override
	protected boolean isResourceRelevant(Path sourcePath, Resource r) {
		return sourcePath.toString().contains(modelsDirSubpath.toString());
	}
}
