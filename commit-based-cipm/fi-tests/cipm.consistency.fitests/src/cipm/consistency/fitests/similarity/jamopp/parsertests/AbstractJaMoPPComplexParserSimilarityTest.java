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
		return Paths.get(super.getRootDirPath().toString(), complexModelImplDirName);
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
	 * @return whether both sides' contents ({@code res.getAllContents()}) have the
	 *         same size.
	 */
	public boolean allContentSizeEqual(Resource lhs, Resource rhs) {
		var lhsIt = lhs.getAllContents();
		var rhsIt = rhs.getAllContents();
		while (lhsIt.hasNext() && rhsIt.hasNext()) {
			lhsIt.next();
			rhsIt.next();
		}
		return !lhsIt.hasNext() && !rhsIt.hasNext();
	}

	/**
	 * @return whether both sides' contents ({@code res.getAllContents()}) have the
	 *         same size.
	 */
	public boolean allContentSizeEqual(EObject lhs, EObject rhs) {
		var lhsIt = lhs.eAllContents();
		var rhsIt = rhs.eAllContents();
		while (lhsIt.hasNext() && rhsIt.hasNext()) {
			lhsIt.next();
			rhsIt.next();
		}
		return !lhsIt.hasNext() && !rhsIt.hasNext();
	}

	/**
	 * Checks if both sides' contents ({@code res.getAllContents()}) are similar, if
	 * their order does not matter. Also checks whether their contents have the same
	 * size.
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean allContentSimilar(Resource lhs, Resource rhs) {
		// Contents cannot be equal, if one side has more contents
		if (!this.allContentSizeEqual(lhs, rhs)) {
			return false;
		}

		var lhsIt = lhs.getAllContents();
		while (lhsIt.hasNext()) {
			var lhsElem = lhsIt.next();
			final var res = new Boolean[] { null };
			var rhsIt = rhs.getAllContents();
			while (res[0] == null && rhsIt.hasNext()) {
				var rhsElem = rhsIt.next();
				// Make sure that all their nested content is similar as well
				if (this.isSimilar(lhsElem, rhsElem) && this.allContentSimilar(lhsElem, rhsElem)) {
					lhsElem.eAllContents();
					// Similar content found, go back to the outer while-loop
					res[0] = Boolean.TRUE;
					break;
				}
			}
			if (res[0] != Boolean.TRUE) {
				// No similar content found, resources' contents are not equal
				return false;
			}
		}
		return true;
	}

	/**
	 * Checks if both sides' contents ({@code res.getAllContents()}) are similar, if
	 * their order does not matter. Also checks whether their contents have the same
	 * size. <br>
	 * <br>
	 * <b>!!! DOES NOT check the similarity of lhs and rhs !!!<b>
	 * 
	 * @return Whether all contents of lhs and rhs are similar, i.e. if all contents
	 *         of lhs have a corresponding similar content on rhs.
	 */
	public boolean allContentSimilar(EObject lhs, EObject rhs) {
		// Contents cannot be equal, if one side has more contents
		if (!this.allContentSizeEqual(lhs, rhs)) {
			return false;
		}

		var lhsIt = lhs.eAllContents();
		while (lhsIt.hasNext()) {
			var lhsElem = lhsIt.next();
			final var res = new Boolean[] { null };
			var rhsIt = rhs.eAllContents();
			while (res[0] == null && rhsIt.hasNext()) {
				var rhsElem = rhsIt.next();
				if (this.isSimilar(lhsElem, rhsElem)) {
					lhsElem.eAllContents();
					// Similar content found, go back to the outer while-loop
					res[0] = Boolean.TRUE;
					break;
				}
			}
			if (res[0] != Boolean.TRUE) {
				// No similar content found, resources' contents are not equal
				return false;
			}
		}
		return true;
	}

	@TestFactory
	public Collection<DynamicNode> testAllContentsSimilarity() {
		var tests = new ArrayList<DynamicNode>();

		this.getModelParentDirsWithinRoot().forEach((md) -> {
			var modelDirName = this.getModelsParentDirName(md);

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
								this.testSimilarityOfAllContents(res1, res2, this.getFileUtil().areContentsEqual(path1, path2));
							}));
				}
			}

			tests.add(DynamicContainer.dynamicContainer(String.format("model = %s (eAllContents)", modelDirName),
					modelTests));
		});

		var result = new ArrayList<DynamicNode>();
		result.add(DynamicContainer
				.dynamicContainer(String.format("root = %s", getRootDirPath().getFileName().toString()), tests));
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
			var modelDirName = this.getModelsParentDirName(md);

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

			tests.add(DynamicContainer.dynamicContainer(String.format("model = %s (eContents)", modelDirName),
					modelTests));
		});

		var result = new ArrayList<DynamicNode>();
		result.add(DynamicContainer
				.dynamicContainer(String.format("root = %s", getRootDirPath().getFileName().toString()), tests));
		return result;
	}
}
