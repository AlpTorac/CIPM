package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.AfterEach;
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

	private static final String artificialResourceName = "ArtificialResource";
	private static final String artificialResourceFileName = artificialResourceName + ".java";

	@AfterEach
	@Override
	public void tearDown() {
		this.getLogger().debug("Tearing down after parser test");
		var cachedResources = resourceCache.getCachedResources();

		// TODO Fix parser repo tests
		if (this.shouldSaveCachedResources()) {
			this.getLogger().debug("Saving all cached resources after parser test");
			for (var res : cachedResources) {
				Assertions.assertTrue(this.getResourceHelper().saveResourceIfNotSaved(res),
						String.format("Could not save %s", res.getURI()));
				if (res.getResourceSet() != null && this.getArtificialResource(res.getResourceSet()) != null) {
					Assertions.assertTrue(
							this.getResourceHelper()
									.saveResourceIfNotSaved(this.getArtificialResource(res.getResourceSet())),
							String.format("Could not save %s", res.getURI()));
				}
			}
			this.getLogger().debug("Saved all cached resources after parser test");
		}

		if (this.shouldDeleteAllResources()) {
			this.getLogger().debug("Deleting all cached resources after parser test");
			for (var res : cachedResources) {
				Assertions.assertTrue(this.getResourceHelper().deleteResource(res),
						String.format("Could not delete %s", res.getURI()));
				if (res.getResourceSet() != null && this.getArtificialResource(res.getResourceSet()) != null) {
					Assertions.assertTrue(
							this.getResourceHelper().deleteResource(this.getArtificialResource(res.getResourceSet())),
							String.format("Could not delete %s", res.getURI()));
				}
			}
			this.getLogger().debug("Deleted all cached resources after parser test");
		} else if (this.shouldUnloadAllResources()) {
			this.getLogger().debug("Unloading all cached resources after parser test");
			for (var res : cachedResources) {
				Assertions.assertTrue(this.getResourceHelper().unloadResource(res),
						String.format("Could not delete %s", res.getURI()));
				if (res.getResourceSet() != null && this.getArtificialResource(res.getResourceSet()) != null) {
					Assertions.assertTrue(
							this.getResourceHelper().unloadResource(this.getArtificialResource(res.getResourceSet())),
							String.format("Could not delete %s", res.getURI()));
				}
			}
			this.getLogger().debug("Unloaded all cached resources after parser test");
		}

		if (this.shouldRemoveResourcesFromCache()) {
			this.getLogger().debug("Removing all cached resources from cache after parser test");
			resourceCache.cleanCache();
			this.getLogger().debug("Removed all cached resources from cache after parser test");
		}

		this.getLogger().debug("Tore down after parser test");

		super.tearDown();
	}

	protected long getElapsedSeconds(long startInNanoseconds) {
		return ((System.nanoTime() - startInNanoseconds) / 1000000000);
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
		var resPath = this.getTestModelSaveRootDirectory().resolve(modelSubPath);
		return resPath.toString();
	}

	protected URI getModelResourceURI(Path modelDir) {
		return URI.createFileURI(this.getResourcePathFor(modelDir)).appendFileExtension(getResourceFileExtension());
	}

	protected void setUpModelParser(JaMoPPJDTSingleFileParser parser) {
	}

	protected String getArtificialResourceFileName(String correspondingResourceFileNameWithoutExt) {
		return correspondingResourceFileNameWithoutExt + artificialResourceName + "." + getResourceFileExtension();
	}

	protected URI getArtificialResourceURI(URI correspondingResourceURI) {
		var fileNameWithoutExt = correspondingResourceURI.trimFileExtension().lastSegment();
		var arName = this.getArtificialResourceFileName(fileNameWithoutExt);
		var arURI = correspondingResourceURI.trimSegments(1);
		return arURI.appendSegment(arName);
	}

	protected Resource getArtificialResource(ResourceSet rSet) {
		return rSet.getResources().stream().filter((r) -> r.getURI().toString().contains(artificialResourceFileName))
				.findFirst().orElse(null);
	}

	protected Resource prepareArtificialResource(ResourceSet modelResourceSet, Resource modelResource,
			URI artificialResourceURI) {
		new TrivialRecovery(modelResourceSet).recover();

		var artificialResource = modelResourceSet.getResources().stream()
				.filter((r) -> r.getURI().toString().contains(artificialResourceFileName)).findFirst()
				.orElseGet(() -> null);

		if (artificialResource != null) {
			artificialResource.setURI(artificialResourceURI);

			this.getLogger().debug(String.format("ArtificialResource is parsed and has its URI set to %s",
					artificialResource.getURI()));

			var resArr = modelResourceSet.getResources().toArray(Resource[]::new);

			for (int i = 0; i < resArr.length; i++) {
				var r = resArr[i];
				if (!r.getURI().isFile() && r != artificialResource && r != modelResource) {
					this.getLogger().debug(String.format("Adding Resource %s to ArtificialResource", r.getURI()));
					artificialResource.getContents().addAll(r.getContents());
					this.getLogger().debug(String.format("Added Resource %s to ArtificialResource", r.getURI()));
					modelResourceSet.getResources().remove(r);
					this.getLogger().debug(String.format("Removed (empty) Resource %s from ResourceSet", r.getURI()));
				}
			}

			// Exclude modelResource and artificialResource from resource count
			this.getLogger().debug(String.format("%d/%d resources have been added to ArtificialResource",
					(resArr.length - modelResourceSet.getResources().size()) - 2, resArr.length - 2));

			// Do not handle potential proxies in ArtificialResource, because they belong to
			// internals of native classes, which are irrelevant for the model. Normally
			// there should be no proxies, if the code represented in Resource files is
			// valid.
		}

		return artificialResource;
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
		var parseStartTime = System.nanoTime();

		/*
		 * Default values of ParserOptions are:
		 * 
		 * RESOLVE_ALL_BINDINGS = true
		 * 
		 * RESOLVE_BINDINGS = true
		 * 
		 * RESOLVE_BINDINGS_OF_INFERABLE_TYPES = true
		 * 
		 * CREATE_LAYOUT_INFORMATION = true
		 * 
		 * PREFER_BINDING_CONVERSION = true
		 */

		ParserOptions.CREATE_LAYOUT_INFORMATION.setValue(Boolean.FALSE);
		ParserOptions.REGISTER_LOCAL.setValue(Boolean.TRUE);
		ParserOptions.RESOLVE_EVERYTHING.setValue(Boolean.FALSE);
		ParserOptions.RESOLVE_ALL_BINDINGS.setValue(Boolean.FALSE);

		JaMoPPJDTSingleFileParser parser = new JaMoPPJDTSingleFileParser();
		this.setUpModelParser(parser);
		var rSet = this.createResourceSet();

		parser.setResourceSet(rSet);
		var resourceSet = parser.parseDirectory(modelDir);

		var resCount = resourceSet.getResources().size();
		this.getLogger().debug(String.format("%d resources have been parsed under %s", resCount,
				this.getDisplayNameForModelDir(modelDir)));

		var modelRes = resourceSet.getResources().stream()
				.filter((r) -> r.getURI().toFileString().contains(modelDir.toString())).findFirst().get();

		/*
		 * Attempt to resolve potential proxies that can be resolved prior to
		 * TrivialRecovery, so that it constructs less synthetic elements that are
		 * redundant.
		 * 
		 * This is necessary, because synthetic elements' type can vary and can cause
		 * typing issues during similarity checking, as the (cached) model resource will
		 * use the synthetic elements, even though they are present directly in the
		 * model resource.
		 * 
		 * Examples to this are LocalVariableStatements; which are declared within the
		 * model, are accessible and referenced by IdentifierReferences. Due to the
		 * absence of context information during parsing, they are considered Fields,
		 * unless they are resolved (via EcoreUtil.resolveAll(...) for instance)
		 * directly after being parsed. Not resolving them causes the
		 * IdentifierReferences to point at their synthetic element correspondents
		 * (Fields), as opposed to their declaration in the model resource.
		 */
		EcoreUtil.resolveAll(modelRes);

		var mergedResURI = this.getModelResourceURI(modelDir);
		var mergedResource = this.createResource(mergedResURI);

		var artificialResource = this.prepareArtificialResource(rSet, modelRes,
				this.getArtificialResourceURI(mergedResURI));

		this.getLogger().debug(String.format("Merging non-ArtificialResources"));

		for (var r : resourceSet.getResources()) {
			if (r != artificialResource) {
				this.getLogger().debug(String.format("Including %s into the merged resource", r.getURI()));
				mergedResource.getContents().addAll(r.getContents());
				this.getLogger().debug(String.format("Included %s into the merged resource", r.getURI()));
			}
		}

		this.getLogger().debug(String.format("Merged non-ArtificialResources"));

		this.getLogger().debug(String.format("%s parsed (uncached, %s seconds)",
				this.getDisplayNameForModelDir(modelDir), this.getElapsedSeconds(parseStartTime)));

		// Add ArtificialResource to mergedResource's resource set, so that finding it
		// becomes easier
		if (artificialResource != null) {
			mergedResource.getResourceSet().getResources().add(artificialResource);
		}

		return mergedResource;
	}

	/**
	 * A variant of {@link #parseModelsDirWithCaching(Path, String)} that uses the
	 * given path as cache key (converts it to string via {@code path.toString()})
	 */
	protected Resource parseModelsDirWithCaching(Path modelDir) {
		return this.parseModelsDirWithCaching(modelDir, modelDir.toString());
	}

	/**
	 * Works similar to {@link #parseModelsDirWithCaching(Path)}, except for the
	 * caching part: <br>
	 * <br>
	 * Checks the cache first for previously parsed resources, if cacheKey is not
	 * null. If a resource from the given path was previously parsed and cached
	 * under cacheKey, returns the cached resource instead. If there were no cached
	 * resources for the given path, adds the parsed resource to the cache under
	 * cacheKey.
	 */
	protected Resource parseModelsDirWithCaching(Path modelDir, String cacheKey) {
		var parseStartTime = System.nanoTime();

		var cache = this.getCacheUtil();
		var modelName = this.getDisplayNameForModelDir(modelDir);

		Resource res = null;

		if (cacheKey != null) {
			// Search for the resource in the cache
			if (cache.isInCache(cacheKey)) {
				this.getLogger().debug(String.format("%s is in cache, using cached version", modelName));
				res = cache.getFromCache(cacheKey);
				if (!res.isLoaded())
					this.getResourceHelper().loadResource(res);
			}

			// Search for the resource file in cache save location
			if (res == null) {
				res = this.getResourceHelper().loadResource(this.getModelResourceURI(modelDir));
				if (res != null) {
					this.getLogger().debug(String.format("Loaded %s from its resource file", modelName));
				}
			}
		}

		// Resource is completely new, parse it from scratch
		if (res == null) {
			res = this.parseModelsDirWithoutCaching(modelDir);
		}

		var key = cacheKey != null ? cacheKey : modelDir.toString();
		cache.addToCache(key, res);

		this.getLogger().debug(String.format("%s parsed (with caching, %s seconds)",
				this.getDisplayNameForModelDir(modelDir), this.getElapsedSeconds(parseStartTime)));
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
	protected Path getTestModelSaveRootDirectory() {
		return this.getAbsoluteCurrentDirectory().resolve(cacheSaveDirName);
	}

	/**
	 * @return The path, at which the parsed resource files' URI will point at,
	 *         should they be saved.
	 */
	protected Path getTestModelSavePath() {
		return this.getTestModelSaveRootDirectory()
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

	public Collection<DynamicNode> createTests(Resource[] resArr) {
		var pathArr = new Path[resArr.length];

		for (int i = 0; i < pathArr.length; i++) {
			pathArr[i] = Path.of(resArr[i].getURI().path());
		}

		return this.createTests(pathArr, resArr);
	}

	public Collection<DynamicNode> createTests(Path[] pathArr) {
		var resArr = new Resource[pathArr.length];

		for (int i = 0; i < resArr.length; i++) {
			resArr[i] = this.parseModelsDirWithCaching(pathArr[i]);
		}

		return this.createTests(pathArr, resArr);
	}

	public Collection<DynamicNode> createTests(Path[] pathArr, Resource[] resArr) {
		if (pathArr.length != resArr.length) {
			Assertions.fail("Lengths of path and resource arrays do not match");
		}

		var tests = new ArrayList<DynamicNode>();

		this.getTestGenerationStrategies().stream().map((s) -> s.createTests(pathArr, resArr, getTestFactories()))
				.forEach((col) -> tests.addAll(col));

		return tests;
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

		var modelParentDirs = this.getModelParentDirsWithinRoot();
		var modelDirMap = new HashMap<Path, Collection<File>>();

		for (var parentDir : modelParentDirs) {
			modelDirMap.put(parentDir, this.getAllModelDirsUnder(parentDir));
		}

		var testsForModelParentDirs = new ArrayList<DynamicNode>();
		modelParentDirs.forEach((md) -> {
			final var modelDirs = modelDirMap.get(md);

			var testsForModelDirs = this.createTests(modelDirs.stream().map(d -> d.toPath()).toArray(Path[]::new));

			testsForModelParentDirs.add(DynamicContainer.dynamicContainer(
					String.format("model = %s", this.getModelsParentDirDisplayName(md)), testsForModelDirs));
		});

		tests.add(DynamicContainer.dynamicContainer(String.format("root = %s", this.getRootDirDisplayName()),
				testsForModelParentDirs));

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

	protected abstract Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies();

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

	/**
	 * Parser tests require the created resource files to persist across tests, as
	 * they are cached. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public boolean shouldDeleteAllResources() {
		return false;
	}

	/**
	 * Parser tests require the created resource files to persist across tests, as
	 * they are cached. <br>
	 * <br>
	 * {@inheritDoc}
	 */
	@Override
	public boolean shouldUnloadAllResources() {
		return false;
	}

	/**
	 * Override in implementors, if necessary.
	 * 
	 * @return Whether the cached resources should be saved after each test.
	 */
	public boolean shouldSaveCachedResources() {
		return true;
	}

	/**
	 * Override in implementors, if necessary.
	 * 
	 * @return Whether cached resources should be removed after each test.
	 */
	public boolean shouldRemoveResourcesFromCache() {
		return false;
	}
}
