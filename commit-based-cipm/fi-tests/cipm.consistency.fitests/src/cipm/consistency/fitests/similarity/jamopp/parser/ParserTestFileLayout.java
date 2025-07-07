package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.File;
import java.nio.file.Path;

import org.eclipse.emf.common.util.URI;

public class ParserTestFileLayout {
	private Path modelSourceFileRootDirPath;
	/**
	 * The relative path to the directory, where parsed model resource files are to
	 * be saved (if desired).
	 */
	private Path testModelResourceFilesSaveDirPath;

	/**
	 * The relative path to the directory, where the contents of
	 * {@link #resourceCache} are to be saved (if desired).
	 */
	private Path cacheSaveDirPath;

	/**
	 * The relative path to the directory, where time measurements are to be saved
	 * (if desired).
	 */
	private Path timeMeasurementsFileSavePath;

	private String modelResourceFileExtension;

	public ParserTestFileLayout() {
	}

	public ParserTestFileLayout(ParserTestFileLayout layout) {
		this.testModelResourceFilesSaveDirPath = layout.testModelResourceFilesSaveDirPath;
		this.cacheSaveDirPath = layout.cacheSaveDirPath;
		this.timeMeasurementsFileSavePath = layout.timeMeasurementsFileSavePath;
		this.modelResourceFileExtension = layout.modelResourceFileExtension;
	}

	public void setModelSourceFileRootDirPath(Path modelSourceFileRootDirPath) {
		this.modelSourceFileRootDirPath = modelSourceFileRootDirPath;
	}

	public void setModelResourceFileExtension(String modelResourceFileExtension) {
		this.modelResourceFileExtension = modelResourceFileExtension;
	}

	public String getModelResourceFileExtension() {
		return this.modelResourceFileExtension;
	}

	public void setTimeMeasurementsFileSavePath(Path timeMeasurementsFileSavePath) {
		this.timeMeasurementsFileSavePath = timeMeasurementsFileSavePath;
	}

	public void setCacheSaveDirPath(Path cacheSaveDirPath) {
		this.cacheSaveDirPath = cacheSaveDirPath;
	}

	public void setTestModelResourceFilesSaveDirPath(Path testModelResourceFilesSaveDirPath) {
		this.testModelResourceFilesSaveDirPath = testModelResourceFilesSaveDirPath;
	}

	/**
	 * @param modelDir The path to the model source file directory
	 * @return The key, with which the model resource parsed under the given path
	 *         will be added to the cache.
	 */
	public String getCacheKeyForModelSourceFileDir(Path modelDir) {
		return this.getAbsoluteCurrentDirectory().relativize(modelDir).toString();
	}

	/**
	 * @return The absolute path, at which taken time measurements are to be saved.
	 */
	public Path getTimeMeasurementsFileSavePath() {
		return this.getAbsoluteCurrentDirectory().resolve(timeMeasurementsFileSavePath);
	}

	/**
	 * @return The absolute path, at which all files generated througout the tests
	 *         should be saved.
	 */
	public Path getTestFilesSavePath() {
		return this.getAbsoluteCurrentDirectory().resolve(testModelResourceFilesSaveDirPath);
	}

	/**
	 * @param modelDir The path to a directory, which has files for one (and only
	 *                 one) model
	 * @return The path (as String), where the parsed model resource (for the model
	 *         under the given path) should be saved, if desired.
	 */
	public String getModelResourcePathFor(Path modelDir) {
		var modelSubPath = this.getAbsoluteCurrentDirectory().relativize(modelDir);
		var resPath = this.getModelResourceSaveRootDirectory().resolve(modelSubPath);

		var resPathString = resPath.toString();

		// Check if the resource path has a file extension
		// If not, append the file extension for it
		if (!resPath.getFileName().toString().contains(".")) {
			resPathString += "." + this.modelResourceFileExtension;
		}

		return resPathString;
	}

	/**
	 * @param modelDir The path to a directory, which has files for one (and only
	 *                 one) model
	 * @return The physical URI of the model resource parsed from the model at the
	 *         given path.
	 */
	public URI getModelResourceURI(Path modelDir) {
		return URI.createFileURI(this.getModelResourcePathFor(modelDir));
	}

	/**
	 * Defaults to {@link #getAbsoluteCurrentDirectory()}.
	 * 
	 * @return Path to the root folder of the model source file directories
	 */
	public Path getModelSourceFileRootDirPath() {
		return this.modelSourceFileRootDirPath;
	}

	/**
	 * @return The current position within the file system.
	 */
	public Path getAbsoluteCurrentDirectory() {
		return new File("").getAbsoluteFile().toPath();
	}

	/**
	 * @return The root directory, under which generated test resources will be
	 *         saved.
	 */
	public Path getModelResourceSaveRootDirectory() {
		return this.getAbsoluteCurrentDirectory().resolve(cacheSaveDirPath);
	}
}
