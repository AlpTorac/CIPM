package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public abstract class AbstractJaMoPPParserSimilarityTestFactory extends AbstractJaMoPPParserSimilarityTest {

	/**
	 * Ensures that all contents of the parsed models are only then similar
	 * (accounting for their order too), if the content of their source files are
	 * equal (in terms of code, not whitespace nor comments etc.).
	 */
	@TestFactory
	public Collection<DynamicNode> testAllContentsSimilarity() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			final var modelDirs = this.getAllModelDirsUnder(md);
			var modelTests = new ArrayList<DynamicNode>();
			for (var it1 = modelDirs.iterator(); it1.hasNext();) {
				var path1 = it1.next().toPath();
				var res1 = this.parseModelsDirWithCaching(path1);

				for (var it2 = modelDirs.iterator(); it2.hasNext();) {
					var path2 = it2.next().toPath();
					var res2 = this.parseModelsDirWithCaching(path2);

					modelTests.add(DynamicTest
							.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
								this.testSimilarityOfAllContentsRecursively(res1, res2,
										this.getFileUtil().areContentsEqual(path1, path2));
							}));
				}
			}

			tests.add(DynamicContainer
					.dynamicContainer(String.format("model = %s (areSimilar on eAllContents [recursively])",
							this.getModelsParentDirDisplayName(md)), modelTests));
		});

		var result = new ArrayList<DynamicNode>();
		result.add(DynamicContainer.dynamicContainer(String.format("root = %s", this.getRootDirDisplayName()), tests));
		return result;
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with res2.
	 */
	@TestFactory
	public Collection<DynamicNode> testSimilarityWithModelComparison() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			final var modelDirs = this.getAllModelDirsUnder(md);
			var modelTests = new ArrayList<DynamicNode>();
			for (var it1 = modelDirs.iterator(); it1.hasNext();) {
				var path1 = it1.next().toPath();
				var res1 = this.parseModelsDirWithCaching(path1);

				for (var it2 = modelDirs.iterator(); it2.hasNext();) {
					var path2 = it2.next().toPath();
					var res2 = this.parseModelsDirWithCaching(path2);

					modelTests.add(DynamicTest
							.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
								this.testSimilarityWithModelComparison(res1, res2,
										this.getExpectedSimilarityResultForModelComparison(res1, path1, res2, path2));
							}));
				}
			}

			tests.add(DynamicContainer
					.dynamicContainer(String.format("model dir = %s (Java model comparison on both sides)",
							this.getModelsParentDirDisplayName(md)), modelTests));
		});

		var result = new ArrayList<DynamicNode>();
		result.add(DynamicContainer.dynamicContainer(String.format("root = %s", this.getRootDirDisplayName()), tests));
		return result;
	}
}
