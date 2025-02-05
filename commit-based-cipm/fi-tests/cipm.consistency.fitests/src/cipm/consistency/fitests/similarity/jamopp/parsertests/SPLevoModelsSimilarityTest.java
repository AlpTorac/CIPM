package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

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
	 * Path to the root folder of the models from SPLevo
	 */
	private static final String splevoModelImplPath = new File("").getAbsoluteFile().getAbsolutePath() + File.separator
			+ splevoModelImplDirName;

	/**
	 * The first model to parse.
	 */
	private static final String model1Name = "a";
	/**
	 * The second model to parse.
	 */
	private static final String model2Name = "b";

	@Override
	protected Path getRootDir() {
		return Paths.get(splevoModelImplPath);
	}

	@Override
	protected boolean isModelDirectory(File f) {
		return f.getName().equals(model1Name) || f.getName().equals(model2Name);
	}

	@Override
	protected Predicate<Resource> getResourceFilter() {
		return (r) -> r.getURI().path().contains(splevoModelImplDirName);
	}

	/**
	 * Checks if parsed {@link Resource} instances are detected as similar. Checks
	 * the similarity of res1 with itself (same reference) and res2 with itself
	 * (same reference).
	 */
	@TestFactory
	public Collection<DynamicNode> sameResourceSimilarityTest() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithin(splevoModelImplPath).forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			var res1 = parseModelsDir(model1Path);
			var res2 = parseModelsDir(model2Path);

			var dt1 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model1Path), () -> {
				this.testSimilarity(res1, res1, true);
			});
			var dt2 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model2Path), () -> {
				this.testSimilarity(res2, res2, true);
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

		this.getModelParentDirsWithin(splevoModelImplPath).forEach((md) -> {
			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			var res11 = parseModelsDir(model1Path);
			var res12 = parseModelsDir(model1Path);

			var res21 = parseModelsDir(model2Path);
			var res22 = parseModelsDir(model2Path);

			var dt1 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model1Path), () -> {
				this.testSimilarity(res11, res12, true);
			});
			var dt2 = DynamicTest.dynamicTest(getDisplayNameForModelDir(model2Path), () -> {
				this.testSimilarity(res21, res22, true);
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

		this.getModelParentDirsWithin(splevoModelImplPath).forEach((md) -> {
			var modelDirName = this.getModelsParentDirName(md);

			var model1Path = Paths.get(md.toString(), model1Name);
			var model2Path = Paths.get(md.toString(), model2Name);

			final var expectedResult = this.areContentsEqual(model1Path, model2Path);

			this.getLogger().debug(md.getFileName() + " contents equal: " + expectedResult);

			var res1 = parseModelsDir(model1Path);
			var res2 = parseModelsDir(model2Path);

			tests.add(DynamicTest.dynamicTest(modelDirName + " (" + model1Name + " and " + model2Name + ")", () -> {
				this.testSimilarity(res1, res2, expectedResult);
			}));
		});

		return tests;
	}
}
