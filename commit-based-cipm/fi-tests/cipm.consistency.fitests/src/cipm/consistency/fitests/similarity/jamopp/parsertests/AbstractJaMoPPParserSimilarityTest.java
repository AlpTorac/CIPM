package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;

import cipm.consistency.commitintegration.diff.util.JavaModelComparator;
import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import jamopp.options.ParserOptions;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;

/**
 * An abstract test class, which can be used for implementing tests that involve
 * parsing models from Java-related files and checking their similarity.
 * 
 * @author Alp Torac Genc
 */
public abstract class AbstractJaMoPPParserSimilarityTest extends AbstractJaMoPPSimilarityTest {
	/**
	 * An object that caches and grants access to the parsed models, which were
	 * cached after being parsed. <br>
	 * <br>
	 * Make sure that it persists throughout tests, which are supposed to make use
	 * of it.
	 * 
	 * @see {@link #parseModelsDirWithoutCaching(Path)}
	 * @see {@link #parseModelsDirWithCaching(Path)}
	 */
	private static final CacheUtil resourceCache = new CacheUtil();

	/**
	 * @return A utility object that can be used to perform file operations.
	 */
	protected FileUtil getFileUtil() {
		return new FileUtil();
	}

	/**
	 * @return A utility object, which encapsulates caching logic (for parsed
	 *         models) and can be used to hasten tests.
	 */
	protected CacheUtil getCacheUtil() {
		return resourceCache;
	}

	/**
	 * Intended to be used for caching parsed models. Can be overridden to allow
	 * custom cache keys in implementing test classes.
	 * 
	 * @return Generates a cache key from the given path.
	 */
	protected String pathToCacheKey(Path path) {
		return path.toString();
	}

	/**
	 * Explores sub-directories of rootPath recursively for models' parent
	 * directories. Model parent directories are directories, which contain model
	 * directories. <br>
	 * <br>
	 * There is a distinction between a model parent directory and a model
	 * directory, because a model parent directory may contain multiple model
	 * directories.
	 * 
	 * @return A collection of paths to all models' parent directories that can be
	 *         found under root path.
	 */
	protected Collection<Path> getModelParentDirsWithin(String rootPath) {
		return new ArrayList<Path>(this.discoverFiles(new File(rootPath)));
	}

	/**
	 * A variant of {@link #getModelParentDirsWithin(String)} that uses
	 * {@link #getRootDirPath()}.
	 */
	protected Collection<Path> getModelParentDirsWithinRoot() {
		return this.getModelParentDirsWithin(this.getRootDirPath().toString());
	}

	/**
	 * Parses all Java-Model files under the given directory into a {@link Resource}
	 * instance. Uses no means of caching. <br>
	 * <br>
	 * <b>Note: This method will parse ALL such files. Therefore, the given model
	 * directory should only contain one Java-Model.</b>
	 * 
	 * @param modelDir A directory that directly contains the Java-model files
	 * 
	 * @see {@link #isResourceRelevant()}
	 */
	protected Resource parseModelsDirWithoutCaching(Path modelDir) {
		// Leave out commented options
		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
		ParserOptions.REGISTER_LOCAL.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.TRUE);

		JaMoPPJDTSingleFileParser parser = new JaMoPPJDTSingleFileParser();
		parser.setResourceSet(new ResourceSetImpl());
		ResourceSet resourceSet = parser.parseDirectory(modelDir);
		var resCount = resourceSet.getResources().size();
		this.getLogger().debug(String.format("%d resources have been parsed under %s", resCount,
				this.getDisplayNameForModelDir(modelDir)));

		ResourceSet next = new ResourceSetImpl();
		Resource all = next.createResource(URI.createFileURI(this.getTargetPath().toAbsolutePath().toString()));

		var filteredResources = new ArrayList<Resource>();
		resourceSet.getResources().stream().filter((r) -> this.isResourceRelevant(modelDir, r))
				.forEach((r) -> filteredResources.add(r));
		var filteredResCount = filteredResources.size();

		this.getLogger().debug(String.format("%d/%d resources are being used", filteredResCount, resCount));

		for (Resource r : filteredResources) {
			// Filter Resources in ResourceSet that belong in the modelDir
			all.getContents().addAll(r.getContents());
		}
		return all;
	}

	/**
	 * Works similar to {@link #parseModelsDirWithCaching(Path)}, except for the
	 * caching part: <br>
	 * <br>
	 * <b><i>Checks the cache first for previously parsed resources. If a resource
	 * from the given path was previously parsed and cached, returns the cached
	 * resource instead. If there were no cached resources for the given path, adds
	 * the parsed resource to the cache.</i></b>
	 */
	protected Resource parseModelsDirWithCaching(Path modelDir) {
		var cache = this.getCacheUtil();
		var key = this.pathToCacheKey(modelDir);
		if (cache.isInCache(key)) {
			return cache.getFromCache(key);
		}
		var res = this.parseModelsDirWithoutCaching(modelDir);
		cache.addToCache(key, res);
		return res;
	}

	/**
	 * Checks whether the given {@link Resource} instances are similar, based on
	 * {@code res_i.getAllContents()}. The order of the contents is also considered
	 * and will impact the result. <br>
	 * <br>
	 * It is important to use this method over other similarity testing methods, due
	 * to the Java models in these tests being potentially fragmented. Hence the use
	 * of {@code res_i.getAllContents()}. <br>
	 * <br>
	 * <b><i>!!! It is important to note that the result of the similarity checking
	 * in this method will differ from others, because it compares all contents
	 * within the resources and not just root contents. !!!</i></b>
	 */
	protected void testSimilarityOfAllContents(Resource res1, Resource res2, Boolean expectedResult) {
		var list1 = new ArrayList<EObject>();
		var list2 = new ArrayList<EObject>();

		// Java files, which are not in proper project settings,
		// can result in similarity checking issues, if resource.getContents()
		// is used to return the EObject contents.
		// getAllContents() bypasses this, as it makes sure that everything
		// is visited.
		res1.getAllContents().forEachRemaining((o) -> list1.add(o));
		res2.getAllContents().forEachRemaining((o) -> list2.add(o));

		Assertions.assertEquals(expectedResult, this.areSimilar(list1, list2));
	}

	/**
	 * Asserts that the result of similarity checking via model comparison results
	 * in differences or not (denoted by expectedResult). <br>
	 * <br>
	 * Compares res1 and res2, as well as res2 and res1; in order to ensure that the
	 * comparison is symmetric.
	 */
	protected void testSimilarityWithModelComparison(Resource res1, Resource res2, Boolean expectedResult) {
		var cmp1To2 = JavaModelComparator.compareJavaModels(res1, res2, null, null, null);
		var cmp2To1 = JavaModelComparator.compareJavaModels(res2, res1, null, null, null);
		Assertions.assertEquals(expectedResult, cmp1To2.getDifferences().size() == 0);
		Assertions.assertEquals(expectedResult, cmp2To1.getDifferences().size() == 0);
	}

	/**
	 * @param modelDir A directory that directly contains the Java-model files
	 * @return The test display name for the given modelPath
	 */
	protected String getDisplayNameForModelDir(Path modelPath) {
		var nameCount = modelPath.getNameCount();

		var startIndex = nameCount > 2 ? nameCount - 2 : nameCount - 1;
		var endIndex = nameCount;

		return modelPath.subpath(startIndex, endIndex).toString();
	}

	/**
	 * @param modelParentDirPath A directory, which contains other directories that
	 *                           contain Java-model files.
	 * @return The test display name for the given modelParentDirPath
	 */
	protected String getModelsParentDirDisplayName(Path modelParentDirPath) {
		return this.getRootDirPath().relativize(modelParentDirPath).toString();
	}

	protected Collection<File> getAllModelDirsUnder(Path modelParentDirPath) {
		var result = new ArrayList<File>();
		var dirs = modelParentDirPath.toFile().listFiles();
		for (var dir : dirs) {
			if (this.isModelDirectory(dir)) {
				result.add(dir);
			}
		}
		return result;
	}

	/**
	 * Recursively searches for directories containing Java-Model files, starting
	 * from the given directory, and returns a list of all such directories.
	 */
	protected Collection<Path> discoverFiles(File dirToDiscover) {
		var foundModelDirs = new ArrayList<Path>();
		discoverFiles(dirToDiscover, foundModelDirs);
		return foundModelDirs;
	}

	/**
	 * Recursively searches for directories that contain Java-model files. All
	 * directories with pre-defined model names (currently {@link #model1Name} and
	 * {@link #model2name}) will be added to foundModelDirs, if not already there.
	 * 
	 * @param dirToDiscover  The directory, where the recursive search will begin
	 * @param foundModelDirs A collection of directories that contain Java-model
	 *                       files
	 */
	protected void discoverFiles(File dirToDiscover, Collection<Path> foundModelDirs) {
		if (dirToDiscover != null && dirToDiscover.isDirectory()) {
			var discovered = new ArrayList<File>();

			for (var f : dirToDiscover.listFiles()) {
				if (!this.isModelDirectory(f)) {
					discovered.add(f);
				} else if (!foundModelDirs.contains(dirToDiscover.toPath())) {
					foundModelDirs.add(dirToDiscover.toPath());
				}
			}

			discovered.forEach((d) -> discoverFiles(d, foundModelDirs));
		}
	}

	/**
	 * Defaults to {@link #getAbsoluteCurrentDirectory()}.
	 * 
	 * @return Path to the root folder of the models, whose sub-directories will be
	 *         discovered for Java elements.
	 */
	protected Path getRootDirPath() {
		return Paths.get(this.getAbsoluteCurrentDirectory().getAbsolutePath());
	}

	/**
	 * @return The current position within the file system.
	 */
	protected File getAbsoluteCurrentDirectory() {
		return new File("").getAbsoluteFile();
	}

	/**
	 * @return The root directory, under which generated test resources will be
	 *         saved.
	 */
	protected File getTargetRootDirectory() {
		return new File(this.getAbsoluteCurrentDirectory(), "testModels");
	}

	/**
	 * @return The path, at which the parsed resource files' URI will point at,
	 *         should they be saved.
	 */
	protected Path getTargetPath() {
		var targetDir = new File(this.getTargetRootDirectory(),
				this.getAbsoluteCurrentDirectory().toPath().relativize(this.getRootDirPath()).toString());
		return targetDir.toPath();
	}

	/**
	 * Defaults to using {@link #isModelDirectoryName(String)} on the file name.
	 * Check the concrete implementation for more details.
	 * 
	 * @param f The file object representing the directory
	 * 
	 * @return Whether a given directory contains any Java elements, from which a
	 *         Java model can be parsed.
	 */
	protected boolean isModelDirectory(File f) {
		return this.isModelDirectoryName(f.getName());
	}

	/**
	 * Check the concrete implementation for more details.
	 * 
	 * @param s The name of the directory
	 * 
	 * @return Whether a given directory contains any Java elements, from which a
	 *         Java model can be parsed.
	 */
	protected abstract boolean isModelDirectoryName(String s);

	/**
	 * @return Whether the resource r (parsed from the given path) is relevant for
	 *         the tests.
	 */
	protected abstract boolean isResourceRelevant(Path sourcePath, Resource r);
}
