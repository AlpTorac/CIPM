package cipm.consistency.fitests.similarity.jamopp.parsertests.splevo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.parsertests.AbstractJaMoPPParserSimilarityTest;

/**
 * A test class that attempts to parse and check similarity of {@link Resource}
 * files.
 * 
 * @author Alp Torac Genc
 */
public class SPLevoModelsSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models from SPLevo
	 */
	private static final String splevoModelImplDirName = "splevo-testmodels";

	/**
	 * The first model to parse.
	 */
	private static final String model1Name = "a";
	/**
	 * The second model to parse.
	 */
	private static final String model2Name = "b";

	@Override
	protected Path getRootDirPath() {
		return Paths.get(super.getRootDirPath().toString(), splevoModelImplDirName);
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		return s.equals(model1Name) || s.equals(model2Name);
	}

	@Override
	protected boolean isResourceRelevant(Path path, Resource r) {
		return path.toString().contains(splevoModelImplDirName);
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with itself (same reference) and res2 with itself
	 * (same reference).
	 */
	@TestFactory
	public Collection<DynamicNode> sameResourceSimilarityTest() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			var res1 = parseModelsDirWithCaching(model1Path);
			var res2 = parseModelsDirWithCaching(model2Path);

			var dt1 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model1Path), () -> {
				this.testSimilarityOfAllContents(res1, res1, true);
			});
			var dt2 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model2Path), () -> {
				this.testSimilarityOfAllContents(res2, res2, true);
			});

			tests.add(DynamicContainer.dynamicContainer(getModelsParentDirName(md), List.of(dt1, dt2)));
		});

		return tests;
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with its clone and res2 with its clone.
	 */
	@TestFactory
	public Collection<DynamicNode> sameFileSimilarityTest() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			var res11 = parseModelsDirWithCaching(model1Path);
			var res12 = parseModelsDirWithCaching(model1Path);

			var res21 = parseModelsDirWithCaching(model2Path);
			var res22 = parseModelsDirWithCaching(model2Path);

			var dt1 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model1Path), () -> {
				this.testSimilarityOfAllContents(res11, res12, true);
			});
			var dt2 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model2Path), () -> {
				this.testSimilarityOfAllContents(res21, res22, true);
			});

			tests.add(DynamicContainer.dynamicContainer(getModelsParentDirName(md), List.of(dt1, dt2)));
		});

		return tests;
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with res2.
	 */
	@TestFactory
	public Collection<DynamicNode> differentFileSimilarityTest() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			var modelDirName = this.getModelsParentDirName(md);

			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			final var expectedResult = this.getFileUtil().areContentsEqual(model1Path, model2Path);

			this.getLogger().debug(md.getFileName() + " contents equal: " + expectedResult);

			var res1 = parseModelsDirWithCaching(model1Path);
			var res2 = parseModelsDirWithCaching(model2Path);

			tests.add(DynamicTest.dynamicTest(modelDirName + " (" + model1Name + " and " + model2Name + ")", () -> {
				this.testSimilarityOfAllContents(res1, res2, expectedResult);
			}));
		});

		return tests;
	}
}
