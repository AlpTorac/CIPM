package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

/**
 * A utility class that contains file-related operations.
 * 
 * @author Alp Torac Genc
 */
public class FileUtil {
	private Logger logger;

	public FileUtil() {
	}

	/**
	 * Constructs an instance with the given logger and logs messages, as its
	 * methods are called.
	 */
	public FileUtil(Logger logger) {
		this.logger = logger;
	}

	/**
	 * Intended to be called from its future sub-classes (if any).
	 * 
	 * @return The logger this instance has.
	 * @see {@link FileUtil#FileUtil(Logger)}
	 */
	protected Logger getLogger() {
		return this.logger;
	}

	/**
	 * Logs the given message, if it has a logger. Can be overridden in conjunction
	 * with {@link #getLogger()}, in order to change the way messages are logged.
	 */
	protected void logMessage(String msg) {
		var logger = this.getLogger();
		if (logger != null) {
			logger.debug(msg);
		}
	}

	/**
	 * @return Whether the content of both dirs are similar.
	 * 
	 * @see {@link #filesEqual(File, File)}
	 * @see {@link #dirsEqual(File, File)}
	 */
	public boolean areContentsEqual(Path path1, Path path2) {
		var contentEquality = false;

		try {
			contentEquality = dirsEqual(path1.toFile(), path2.toFile());
		} catch (IOException e) {
			var msg = "Could not read paths: " + path1.toString() + " and " + path2.toString();
			this.logMessage(msg);
			throw new IllegalArgumentException(msg);
		}

		return contentEquality;
	}

	/**
	 * Reads the given file and removes line breaks and whitespaces.
	 */
	public String readEffectiveCode(File f) throws IOException {
		var content = Files.readString(f.toPath());

		return content.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}

	/**
	 * Compares the equality of the given files based on their effective content.
	 * 
	 * @see {@link #readEffectiveCode(File)}
	 */
	public boolean filesEqual(File f1, File f2) throws IOException {
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
	public boolean dirsEqual(File dir1, File dir2) throws IOException {
		this.logMessage("Comparing: " + dir1.getName() + " and " + dir2.getName());

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
					this.logMessage("Directories " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			} else if (f1.isFile() && f2.isFile()) {
				if (!filesEqual(f1, f2)) {
					this.logMessage("Files " + f1.getName() + " and " + f2.getName() + " are not equal");
					return false;
				}
			} else {
				this.logMessage("Unexpected case there is a file and a directory");
				return false;
			}
		}

		return true;
	}

	/**
	 * Recursively cleans files, which have been used in tests.
	 * 
	 * @param path The path to the directory to clean
	 */
	public void cleanModels(Path path) {
		var file = path.toFile();

		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
				return;
			}

			if (file.isDirectory()) {
				var children = file.listFiles();

				if (children != null) {
					for (File cf : children) {
						this.cleanModels(cf.toPath());
					}
				}

				file.delete();
			}
		}
	}

	/**
	 * Recursively copies files from the given parent parameter to the path given
	 * via copyPath. Replaces files, which already exist.
	 * 
	 * @param parentPath The directory to copy
	 * @param copyPath   The path, where everything under parentPath will be copied.
	 */
	public void copyModels(Path parentPath, Path copyPath) throws IOException {
		File parent = parentPath.toFile();
		this.getLogger().debug("Copying the contents of " + parent.getAbsolutePath() + " into " + copyPath);
		for (File f : parent.listFiles()) {
			var fileName = f.getName();

			// Skip non-java files, since they are irrelevant
			if ((f.isDirectory() && fileName.contains(".git")) || (f.isFile() && !fileName.contains(".java"))) {
				continue;
			}

			if (f.isDirectory()) {
				this.getLogger().debug("Directory found: " + fileName);
				String newCopyAddress = copyPath + File.separator + fileName;
				this.getLogger().debug("Copy address changed to " + newCopyAddress);
				File tmpDir = new File(newCopyAddress);

				this.copyModels(f.toPath(), tmpDir.toPath());
			}
			if (f.isFile()) {
				this.getLogger().debug("File found: " + fileName);
				File tmpFile = new File(copyPath + File.separator + fileName);

				if (tmpFile.exists()) {
					this.getLogger().debug("Existing file will be replaced");
				} else {
					this.getLogger().debug("Creating file");
					tmpFile.mkdirs();
					this.getLogger().debug("Created file");
				}

				this.getLogger().debug("Copying original file into new file");
				Files.copy(f.toPath(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				this.getLogger().debug("Copied original file into new file: " + fileName);

				this.getLogger().debug("Verifying equality of file content");
				Assertions.assertTrue(Files.readString(f.toPath()).equals(Files.readString(tmpFile.toPath())));
				this.getLogger().debug("Verified equality of file content");
			}
		}
		this.getLogger().debug("Parent directory " + parent.getName() + " has been copied");
	}
}
