package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class ComplexModelsSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final String complexModelImplDirName = "complex-testmodels";
	/**
	 * Path to the root folder of the models from SPLevo
	 */
	private static final String complexModelImplPath = new File("").getAbsoluteFile().getAbsolutePath() + File.separator
			+ complexModelImplDirName;

	@Override
	protected Path getRootDir() {
		return Paths.get(complexModelImplPath);
	}

	@Override
	protected boolean isModelDirectoryName(String s) {
		// TODO Replace once a better method is found for this

		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	protected Predicate<String> getResourceNameFilter() {
		return (s) -> s.contains(complexModelImplDirName);
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

			final var modelDirs = this.getAllModelDirsUnder(md);
			final var resources = new ArrayList<Resource>();
			modelDirs.stream().forEach((m) -> {
				resources.add(parseModelsDir(m.toPath()));
			});
			
			tests.add(DynamicTest.dynamicTest(modelDirName + " (all contents)", () -> {
				for (var res1 : resources) {
					for (var res2 : resources) {
						this.testSimilarityOfAllContents(res1, res2, res1 == res2);
					}
				}
			}));
			tests.add(DynamicTest.dynamicTest(modelDirName + " (only root contents)", () -> {
				for (var res1 : resources) {
					for (var res2 : resources) {
						// TODO Fix the assertion
						this.testSimilarity(res1, res2, res1 == res2);
					}
				}
			}));
		});

		return tests;
	}
}
