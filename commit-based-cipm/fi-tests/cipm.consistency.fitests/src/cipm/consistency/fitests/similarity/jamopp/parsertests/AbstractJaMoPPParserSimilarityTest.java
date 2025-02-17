package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.jupiter.api.Assertions;

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
	 * @return Whether the content of both dirs are similar.
	 * 
	 * @see {@link #filesEqual(File, File)}
	 * @see {@link #dirsEqual(File, File)}
	 */
	protected boolean areContentsEqual(Path path1, Path path2) {
		var contentEquality = false;

		try {
			contentEquality = dirsEqual(path1.toFile(), path2.toFile());
		} catch (IOException e) {
			this.getLogger().debug("Could not read paths: " + path1.toString() + " and " + path2.toString());
			Assertions.fail();
		}

		return contentEquality;
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
	 * instance. <br>
	 * <br>
	 * <b>Note: This method will parse ALL such files. Therefore, the given model
	 * directory should only contain one Java-Model.</b>
	 * 
	 * @param modelDir A directory that directly contains the Java-model files
	 * 
	 * @see {@link #getResourceFilter()}
	 */
	protected Resource parseModelsDir(Path modelDir) {
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
		resourceSet.getResources().stream().filter(this.getResourceFilter()).forEach((r) -> filteredResources.add(r));
		var filteredResCount = filteredResources.size();

		this.getLogger().debug(String.format("%d/%d resources are being used", filteredResCount, resCount));

		for (Resource r : filteredResources) {
			// Filter Resources in ResourceSet that belong in the modelDir (based on URI)
			all.getContents().addAll(r.getContents());
		}
		return all;
	}

	/**
	 * Checks whether the given {@link Resource} instances are similar, based on
	 * {@code res_i.getAllContents()}. <br>
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
	 * Asserts that the result of similarity checking the root contents
	 * ({@code res.getContents()}) of the given resources is as expected.
	 */
	protected void testSimilarity(Resource res1, Resource res2, Boolean expectedResult) {
		Assertions.assertEquals(expectedResult, this.areSimilar(res1.getContents(), res2.getContents()));
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
	protected String getModelsParentDirName(Path modelParentDirPath) {
		return modelParentDirPath.getName(modelParentDirPath.getNameCount() - 1).toString();
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
	 * Reads the given file and removes line breaks and whitespaces.
	 */
	protected String readEffectiveCode(File f) throws IOException {
		var content = Files.readString(f.toPath());

		return content.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}

	/**
	 * Compares the equality of the given files based on their effective content.
	 * 
	 * @see {@link #readEffectiveCode(File)}
	 */
	protected boolean filesEqual(File f1, File f2) throws IOException {
		var f1Content = readEffectiveCode(f1);
		var f2Content = readEffectiveCode(f2);

		return f1Content.equals(f2Content);
	}

	/**
	 * Recursively checks the equality of the given directories, based on their
	 * content (i.e. the files/sub-directories they contain and the contents of
	 * those files).
	 * 
	 * @see {@link #filesEqual(File, File)}, {@link #readEffectiveCode(File)}
	 */
	protected boolean dirsEqual(File dir1, File dir2) throws IOException {
		this.getLogger().debug("Comparing: " + dir1.getName() + " and " + dir2.getName());

		// There cannot be 2 files with the same path, name and extension
		// so using TreeSet, which sorts the files spares doing so here
		var files1 = new TreeSet<File>();
		var files2 = new TreeSet<File>();

		for (var f : dir1.listFiles()) {
			files1.add(f);
		}

		for (var f : dir2.listFiles()) {
			files2.add(f);
		}

		if (files1.size() != files2.size()) {
			return false;
		}

		var fileIter1 = files1.iterator();
		var fileIter2 = files2.iterator();

		for (int i = 0; i < files1.size(); i++) {
			var f1 = fileIter1.next();
			var f2 = fileIter2.next();

			if (f1.isDirectory() && f2.isDirectory()) {
				if (!dirsEqual(f1, f2)) {
					this.getLogger().debug("Directories " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			} else if (f1.isFile() && f2.isFile()) {
				if (!filesEqual(f1, f2)) {
					this.getLogger().debug("Files " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			} else {
				this.getLogger().debug("Unexpected case there is a file and a directory");
				return false;
			}
		}

		return true;
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
	 * Defaults to filtering the URI path using {@link #getResourceNameFilter()}.
	 * Check the concrete implementation for more details.
	 * 
	 * @return The filter, which will be used to filter out unwanted Java models.
	 */
	protected Predicate<Resource> getResourceFilter() {
		return (r) -> this.getResourceNameFilter().test(r.getURI().path());
	}

	/**
	 * Check the concrete implementation for more details.
	 * 
	 * @return A predicate that encapsulates the algorithm that will be used to
	 *         filter the relevant resources based on their name.
	 */
	protected abstract Predicate<String> getResourceNameFilter();
}
