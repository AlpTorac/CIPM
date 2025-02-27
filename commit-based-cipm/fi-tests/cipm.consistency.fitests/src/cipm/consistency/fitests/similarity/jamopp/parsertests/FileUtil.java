package cipm.consistency.fitests.similarity.jamopp.parsertests;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.TreeSet;

import org.apache.log4j.Logger;

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
}
