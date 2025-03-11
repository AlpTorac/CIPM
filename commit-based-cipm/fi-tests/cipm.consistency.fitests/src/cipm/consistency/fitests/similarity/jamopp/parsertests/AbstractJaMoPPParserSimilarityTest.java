package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.diff.DefaultDiffEngine;
import org.eclipse.emf.compare.diff.DiffBuilder;
import org.eclipse.emf.compare.diff.FeatureFilter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emftext.language.java.JavaPackage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.splevo.jamopp.diffing.diff.JaMoPPFeatureFilter;
import org.splevo.jamopp.diffing.scope.PackageIgnoreChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

import cipm.consistency.commitintegration.diff.util.HierarchicalMatchEngineFactoryGenerator;
import cipm.consistency.commitintegration.diff.util.ResourceListFilteringComparisonScope;
import cipm.consistency.fitests.similarity.jamopp.AbstractJaMoPPSimilarityTest;
import jamopp.options.ParserOptions;
import jamopp.parser.jdt.singlefile.JaMoPPJDTSingleFileParser;
import jamopp.recovery.trivial.TrivialRecovery;

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
	 * Recursively explores obj for nested contents.
	 * 
	 * @param obj A given EObject instance
	 * @return Collection that contains obj and all further EObject instances, which
	 *         are nested in obj as content.
	 */
	protected Collection<EObject> getAllEObjectsRecursively(EObject obj) {
		var allContents = new ArrayList<EObject>();
		allContents.add(obj);
		obj.eAllContents().forEachRemaining((c) -> {
			allContents.addAll(this.getAllEObjectsRecursively(c));
		});
		return allContents;
	}

	/**
	 * Checks whether the given {@link Resource} instances are similar, based on
	 * {@code res_i.getAllContents()}. The order of the contents, as well as nested
	 * contents, is also considered and will impact the result. <br>
	 * <br>
	 * It is important to use this method over other similarity testing methods, due
	 * to the Java models in these tests being potentially fragmented. Hence the use
	 * of {@code res_i.getAllContents()}. <br>
	 * <br>
	 * <b><i>!!! It is important to note that the result of the similarity checking
	 * in this method will differ from others, because it compares all contents
	 * within the resources and not just root contents. !!!</i></b>
	 */
	protected void testSimilarityOfAllContentsRecursively(Resource res1, Resource res2, Boolean expectedResult) {
		var list1 = new ArrayList<EObject>();
		var list2 = new ArrayList<EObject>();

		/*
		 * Only adding all contents as is can yield unexpected results, because doing so
		 * does not necessarily account for the nested contents' order. This is a
		 * problem, because it may lead to comparisons that are not performed by
		 * similarity checking.
		 */
		res1.getAllContents().forEachRemaining((o) -> list1.addAll(this.getAllEObjectsRecursively(o)));
		res2.getAllContents().forEachRemaining((o) -> list2.addAll(this.getAllEObjectsRecursively(o)));

		Assertions.assertEquals(expectedResult, this.areSimilar(list1, list2));
	}

	/**
	 * Compares the given {@link Resource} instances representing Java models. Uses
	 * the underlying similarity checking mechanisms for identifying changes. <br>
	 * <br>
	 * Note that the order of the given parameters matters and will influence the
	 * result, since reaching from one side to the other will require "opposite"
	 * operations.
	 * 
	 * @param res1 The new state
	 * @param res2 The old state
	 * @return Result of comparing {@code res2} to {@code res1}, i.e. what needs to
	 *         be done to {@code res2} to get to {@code res1}.
	 * 
	 * @see {@link #getSCC()}
	 */
	protected Comparison compareModels(Resource res1, Resource res2) {

		var scope = new ResourceListFilteringComparisonScope(res1, res2, null, null);
		scope.getNsURIs().add(JavaPackage.eNS_URI);

		var jamoppFeatureFilter = new JaMoPPFeatureFilter(new PackageIgnoreChecker(List.of()));
		var diffProcessor = new DiffBuilder();
		var diffEngine = new DefaultDiffEngine(diffProcessor) {
			@Override
			protected FeatureFilter createFeatureFilter() {
				return jamoppFeatureFilter;
			}
		};

		var engineRegistry = HierarchicalMatchEngineFactoryGenerator.generateMatchEngineRegistry(
				HierarchicalMatchEngineFactoryGenerator.generateMatchEngineFactory(new ISimilarityChecker() {

					@Override
					public Boolean isSimilar(Object element1, Object element2) {
						return getSCC().isSimilar(element1, element2);
					}

					@Override
					public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2) {
						return getSCC().areSimilar(elements1, elements2);
					}

				}, this.getResourceFileExtension()));

		var builder = EMFCompare.builder().setMatchEngineFactoryRegistry(engineRegistry).setDiffEngine(diffEngine);

		return builder.build().compare(scope);
	}

	/**
	 * Asserts that the result of similarity checking via model comparison results
	 * in differences or not (denoted by expectedResult). <br>
	 * <br>
	 * Compares res1 and res2, as well as res2 and res1; in order to ensure that the
	 * comparison is symmetric.
	 */
	protected void testSimilarityWithModelComparison(Resource res1, Resource res2, Boolean expectedResult) {
		var cmp1To2 = this.compareModels(res1, res2);
		var cmp2To1 = this.compareModels(res2, res1);
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
