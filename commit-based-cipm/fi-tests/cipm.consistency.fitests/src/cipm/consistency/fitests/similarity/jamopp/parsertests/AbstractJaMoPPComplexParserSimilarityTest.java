package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

	private static final Map<String, Resource> resourceCache = new HashMap<>();

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
	 * Adds the given resource with the given key to the cache. Replaces the
	 * resource, if the key is already in the cache.
	 * 
	 * @param key The key associated with the given resource
	 * @param res A given resource
	 */
	protected void addToCache(String key, Resource res) {
		resourceCache.put(key, res);
	}

	/**
	 * @return Gets the resource associated with the given key from the cache. Null,
	 *         if there is no such key in the cache.
	 */
	protected Resource getFromCache(String key) {
		return resourceCache.get(key);
	}

	/**
	 * @return Whether the given key is present in the cache.
	 */
	protected boolean isInCache(String key) {
		return resourceCache.containsKey(key);
	}

	/**
	 * Removes the cached resource associated with the given key.
	 */
	protected void removeFromCache(String key) {
		resourceCache.remove(key);
	}

	/**
	 * Removes all entries from the cache.
	 */
	protected void cleanCache() {
		resourceCache.clear();
	}

	/**
	 * @return Generates a cache key from the given path.
	 */
	protected String pathToCacheKey(Path path) {
		return path.toString();
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * <b><i>Checks the cache first for previously parsed resources. If a resource
	 * from the given path was previously parsed and cached, returns the cached
	 * resource instead. If there were no cached resources for the given path, adds
	 * the parsed resource to the cache.</i></b>
	 */
	@Override
	protected Resource parseModelsDir(Path modelDir) {
		var key = this.pathToCacheKey(modelDir);
		if (this.isInCache(key)) {
			return this.getFromCache(key);
		}
		var res = super.parseModelsDir(modelDir);
		this.addToCache(key, res);
		return res;
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
				var res1 = this.parseModelsDir(path1);

				for (var it2 = modelDirs.iterator(); it2.hasNext();) {
					var path2 = it2.next().toPath();
					var res2 = this.parseModelsDir(path2);

					modelTests.add(DynamicTest
							.dynamicTest(String.format("%s vs %s", path1.getFileName(), path2.getFileName()), () -> {
								this.testSimilarityOfAllContents(res1, res2, this.areContentsEqual(path1, path2));
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
				var res1 = this.parseModelsDir(path1);

				for (var it2 = modelDirs.iterator(); it2.hasNext();) {
					var path2 = it2.next().toPath();
					var res2 = this.parseModelsDir(path2);

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
