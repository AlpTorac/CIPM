package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.types.PrimitiveType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import jamopp.options.ParserOptions;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;
import jamopp.recovery.trivial.TrivialRecovery;

/**
 * An abstract test class, which can be used for implementing tests that involve
 * parsing models from Java-related files and checking their similarity.
 * 
 * @author Alp Torac Genc
 * 
 * @see {@link #createTests()}
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

	private static final String cacheSaveDirName = "testmodel-cache";

	@AfterAll
	public static void tearDownAfterAll() {
		var contents = resourceCache.getAllCacheContent();

		// Save the cached resources
		for (var e : contents.entrySet()) {
			var res = e.getValue();
			var uri = res.getURI();
			if (uri.isFile() && !new File(uri.toFileString()).exists()) {
				try {

					// TODO Fix types not being saved as intended

					res.save(null);
				} catch (IOException excep) {
					excep.printStackTrace();
					Assertions.fail();
				}
			}
		}
	}

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

	protected String getResourcePathFor(Path modelDir) {
		var modelSubPath = this.getAbsoluteCurrentDirectory().relativize(modelDir);
		var resPath = this.getTargetRootDirectory().resolve(modelSubPath);
		return resPath.toString();
	}

	protected URI getResourceURI(Path modelDir) {
		return URI.createFileURI(this.getResourcePathFor(modelDir));
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
		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
		ParserOptions.REGISTER_LOCAL.setValue(Boolean.TRUE);
		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.FALSE);
		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.FALSE);

//		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
//		ParserOptions.PREFER_BINDING_CONVERSION.setValue(Boolean.TRUE);
//		ParserOptions.REGISTER_LOCAL.setValue(Boolean.FALSE);
//		ParserOptions.RESOLVE_BINDINGS.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_BINDINGS_OF_INFERABLE_TYPES.setValue(Boolean.TRUE);
//		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.FALSE);
//		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.FALSE);

		JaMoPPJDTSingleFileParser parser = new JaMoPPJDTSingleFileParser();
		var rSet = new ResourceSetImpl();
		parser.setResourceSet(rSet);
		ResourceSet resourceSet = parser.parseDirectory(modelDir);

		// Wrap all primitive types to ensure that their wrapper classes are loaded.
//		for (var resource : new ArrayList<>(resourceSet.getResources())) {
//			resource.getAllContents().forEachRemaining(obj -> {
//				if (obj instanceof PrimitiveType) {
//					var type = (PrimitiveType) obj;
//					type.wrapPrimitiveType();
//				}
//			});
//		}
//		new TrivialRecovery(resourceSet).recover();

		var resCount = resourceSet.getResources().size();
		this.getLogger().debug(String.format("%d resources have been parsed under %s", resCount,
				this.getDisplayNameForModelDir(modelDir)));

		ResourceSet next = new ResourceSetImpl();
		Resource all = next.createResource(this.getResourceURI(modelDir));

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
		var modelName = this.getDisplayNameForModelDir(modelDir);

		Resource res = null;

		// Search for the resource in the cache
		if (cache.isInCache(key)) {
			this.getLogger().debug(String.format("%s is in cache, using cached version", modelName));
			res = cache.getFromCache(key);
		}

		// Search for the resource file in cache save location
		if (res == null) {
			var uri = this.getResourceURI(modelDir);
			if (uri.isFile() && new File(uri.toFileString()).exists()) {
				this.getLogger().debug(String.format("%s resource file is present, loading it", modelName));
				res = new ResourceSetImpl().createResource(uri);
				try {
					res.load(null);
				} catch (IOException e) {
					e.printStackTrace();
					Assertions.fail();
				}
			}
		}

		// Resource is completely new, parse it from scratch
		if (res == null) {
			res = this.parseModelsDirWithoutCaching(modelDir);
		}

		cache.addToCache(key, res);
		return res;
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
	 * Defaults to the relative path between the root directory
	 * ({@link #getRootDirPath()}) and the given path. If both paths are the same,
	 * returns the last name in the parameter.
	 * 
	 * @param modelParentDirPath A directory, which contains other directories that
	 *                           contain Java-model files.
	 * @return The test display name for the given modelParentDirPath
	 */
	protected String getModelsParentDirDisplayName(Path modelParentDirPath) {
		var rootPath = this.getRootDirPath();
		var relPath = rootPath.relativize(modelParentDirPath);
		var result = relPath.toString();
		if (result.isBlank()) {
			return modelParentDirPath.getFileName().toString();
		}
		return result;
	}

	/**
	 * Defaults to the relative path between the current directory
	 * ({@link #getAbsoluteCurrentDirectory()}) and the root directory
	 * ({@link #getRootDirPath()}).
	 * 
	 * @return The display name for the root directory.
	 * @see {@link #getRootDirPath()}
	 */
	protected String getRootDirDisplayName() {
		return this.getAbsoluteCurrentDirectory().relativize(this.getRootDirPath()).toString();
	}

	/**
	 * @param modelParentDirPath A directory, which potentially contains model
	 *                           directories
	 * @return All model directories under the given path
	 * 
	 * @see {@link #isModelDirectory(File)}
	 */
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
		return this.getAbsoluteCurrentDirectory();
	}

	/**
	 * @return The current position within the file system.
	 */
	protected Path getAbsoluteCurrentDirectory() {
		return new File("").getAbsoluteFile().toPath();
	}

	/**
	 * @return The root directory, under which generated test resources will be
	 *         saved.
	 */
	protected Path getTargetRootDirectory() {
		return this.getAbsoluteCurrentDirectory().resolve(cacheSaveDirName);
	}

	/**
	 * @return The path, at which the parsed resource files' URI will point at,
	 *         should they be saved.
	 */
	protected Path getTargetPath() {
		return this.getTargetRootDirectory()
				.resolve(this.getAbsoluteCurrentDirectory().relativize(this.getRootDirPath()));
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
	 * Generates dynamic tests for each model directories based on the registered
	 * {@link AbstractJaMoPPParserSimilarityTestFactory} instances. Implemented here
	 * in efforts to have a unified template for dynamic test generation. <br>
	 * <br>
	 * <b>Can be overridden in implementors; in order to add preparatory actions,
	 * clean up actions or to change the default test generation. <i> DUE TO HOW
	 * JUNIT WORKS, GENERATED TESTS WILL NOT REGISTER UNLESS ANNOTED AS
	 * {@code TestFactory} IN OVERRIDING VERSIONS TOO. </i></b> <br>
	 * <br>
	 * Unless overridden in implementors, JUnit will detect this method as a
	 * {@link TestFactory}, which will run the tests generated here.
	 * 
	 * @see {@link #getModelParentDirsWithinRoot()} and
	 *      {@link #getAllModelDirsUnder(Path)} for locating model directories
	 * @see {@link TestFactory} for what tests are to be generated
	 */
	@TestFactory
	public Collection<DynamicNode> createTests() {
		var tests = new ArrayList<DynamicNode>();
		var testFactories = this.getTestFactories();

		var modelParentDirs = this.getModelParentDirsWithinRoot();
		var modelDirMap = new HashMap<Path, Collection<File>>();

		for (var parentDir : modelParentDirs) {
			modelDirMap.put(parentDir, this.getAllModelDirsUnder(parentDir));
		}

		testFactories.forEach((tf) -> {
			var testsForModelParentDirs = new ArrayList<DynamicNode>();
			modelParentDirs.forEach((md) -> {
				final var modelDirs = modelDirMap.get(md);
				var testsForModelDirs = new ArrayList<DynamicNode>();
				for (var it1 = modelDirs.iterator(); it1.hasNext();) {
					var path1 = it1.next().toPath();
					var res1 = this.parseModelsDirWithCaching(path1);

					for (var it2 = modelDirs.iterator(); it2.hasNext();) {
						var path2 = it2.next().toPath();
						var res2 = this.parseModelsDirWithCaching(path2);

						testsForModelDirs.add(tf.createTestsFor(res1, path1, res2, path2));
					}
				}

				testsForModelParentDirs.add(DynamicContainer.dynamicContainer(
						String.format("model = %s", this.getModelsParentDirDisplayName(md)), testsForModelDirs));
			});

			tests.add(DynamicContainer.dynamicContainer(
					String.format("root = %s (%s)", this.getRootDirDisplayName(), tf.getTestDescription()),
					testsForModelParentDirs));
		});

		return tests;
	}

	/**
	 * Defaults to all {@link AbstractJaMoPPParserSimilarityTestFactory} instances
	 * generated by {@link AllJaMoPPParserTestFactories}. <br>
	 * <br>
	 * Can be overridden in implementors to modify the generated tests. Therefore,
	 * refer to the overriding version (if existent) for more information.
	 * 
	 * @return Factories of tests that should be generated for each relevant model
	 *         directories.
	 */
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		return new AllJaMoPPParserTestFactories().createFactoriesFor(this.getSCC(), this.getResourceFileExtension(),
				this.doesContentOrderMatter());
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

	/**
	 * Defaults to true. <br>
	 * <br>
	 * Can be overridden in implementors, if necessary.
	 * 
	 * @return Whether the order of model resource contents (i.e. all EObject
	 *         instances nested directly or indirectly within) matters and should be
	 *         accounted for in the expected results.
	 */
	protected boolean doesContentOrderMatter() {
		return true;
	}
}
