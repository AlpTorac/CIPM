package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public abstract class AbstractJaMoPPComplexParserSimilarityTest extends AbstractJaMoPPParserSimilarityTest {
	/**
	 * The name of the root directory of the models
	 */
	private static final String complexModelImplDirName = "complex-testmodels";

	@Override
	protected Path getRootDirPath() {
		var pathToComplexModels = Paths.get(super.getRootDirPath().toString(), complexModelImplDirName);
		return Paths.get(pathToComplexModels.toString(), this.getModelsDirSubpath().toString());
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

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Defaults to checking whether the source path contains
	 * {@link #getModelsDirSubpath()}.
	 */
	@Override
	protected boolean isResourceRelevant(Path sourcePath, Resource r) {
		return sourcePath.toString().contains(this.getModelsDirSubpath().toString());
	}

	/**
	 * Override in concrete tests with the path to the topmost directory, which
	 * contains the model files that will be used by the test.
	 * 
	 * @return The path to the directory, from which onward model directories will
	 *         be searched for this particular test.
	 */
	protected abstract Path getModelsDirSubpath();

	/**
	 * Defaults to comparing the source file paths.
	 * 
	 * @param lhs               Left-hand side resource
	 * @param lhsSourceFilePath The path that the resource lhs was parsed from
	 * @param rhs               Right-hand side resource
	 * @param rhsSourceFilePath The path that the resource rhs was parsed from
	 * @return The expected result of similarity checking the given resources by
	 *         using model comparison
	 * 
	 * @see {@link #testSimilarityWithModelComparison(Resource, Resource, Boolean)}
	 */
	public Boolean getExpectedSimilarityResultForModelComparison(Resource lhs, Path lhsSourceFilePath, Resource rhs,
			Path rhsSourceFilePath) {
		return lhsSourceFilePath.toString().equals(rhsSourceFilePath.toString());
	}

	/**
	 * Checks if both sides' contents ({@code res.getAllContents()}) are similar, if
	 * their order does not matter. Makes sure that the result is the same as
	 * {@code allContentSimilar(rhs, lhs)}.
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean contentwiseSimilar(Resource lhs, Resource rhs) {
		var lhsContent = new ArrayList<EObject>();
		lhs.getAllContents().forEachRemaining((e) -> lhsContent.add(e));
		var rhsContent = new ArrayList<EObject>();
		rhs.getAllContents().forEachRemaining((e) -> rhsContent.add(e));

		return this.contentwiseSimilar(lhsContent, rhsContent) && this.contentwiseSimilar(rhsContent, lhsContent);
	}

	/**
	 * Checks if both sides' contents ({@code obj.eAllContents()}) are similar, if
	 * their order does not matter. Makes sure that the result is the same as
	 * {@code allContentSimilar(rhs, lhs)}.
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean contentwiseSimilar(EObject lhs, EObject rhs) {
		if (!this.isSimilar(lhs, rhs) || !this.isSimilar(rhs, lhs)) {
			return false;
		}

		var lhsContent = new ArrayList<EObject>();
		lhs.eAllContents().forEachRemaining((e) -> lhsContent.add(e));
		var rhsContent = new ArrayList<EObject>();
		rhs.eAllContents().forEachRemaining((e) -> rhsContent.add(e));

		return this.contentwiseSimilar(lhsContent, rhsContent) && this.contentwiseSimilar(rhsContent, lhsContent);
	}

	/**
	 * Variant of {@link #contentwiseSimilar(EObject, EObject)} for collections.
	 */
	public boolean contentwiseSimilar(Collection<EObject> lhs, Collection<EObject> rhs) {
		var lhsContent = new ArrayList<EObject>(lhs);
		var rhsContent = new ArrayList<EObject>(rhs);

		if (lhsContent.size() != rhsContent.size()) {
			return false;
		}

		while (!lhsContent.isEmpty() && !rhsContent.isEmpty()) {
			var lhsElem = lhsContent.get(0);
			final var rhsElem = new EObject[] { null };
			for (var e : rhsContent) {
				if (this.contentwiseSimilar(lhsElem, e)) {
					rhsElem[0] = e;
					break;
				}
			}
			if (rhsElem[0] != null) {
				lhsContent.remove(lhsElem);
				rhsContent.remove(rhsElem[0]);
			} else {
				return false;
			}
		}
		return lhsContent.isEmpty() && rhsContent.isEmpty();
	}

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
