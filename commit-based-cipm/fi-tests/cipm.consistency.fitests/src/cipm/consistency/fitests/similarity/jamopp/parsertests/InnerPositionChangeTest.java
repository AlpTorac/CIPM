package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.resource.Resource;

public class InnerPositionChangeTest extends AbstractJaMoPPComplexParserSimilarityTest {
	private final static Path innerPosModelsDirSubpath = Paths.get("innerClassifier", "innerPositionChange");

	@Override
	protected Path getRootDirPath() {
		return Paths.get(super.getRootDirPath().toString(), innerPosModelsDirSubpath.toString());
	}

	@Override
	protected Predicate<String> getResourceNameFilter() {
		return (s) -> s.contains(innerPosModelsDirSubpath.getFileName().toString());
	}

	@Override
	public Boolean getExpectedContentSimilarityResultFor(Resource res1, Resource res2) {
		return true;
	}
}
