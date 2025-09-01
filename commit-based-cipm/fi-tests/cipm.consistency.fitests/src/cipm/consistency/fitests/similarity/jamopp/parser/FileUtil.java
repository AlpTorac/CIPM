package cipm.consistency.fitests.similarity.jamopp.parser;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import cipm.consistency.fitests.similarity.SimilarityTestLogger;

/**
 * A utility class that contains file-related operations.
 * 
 * @author Alp Torac Genc
 */
public class FileUtil {
	/**
	 * {@link #areContentsEqual(Path, Path, Set, Set)}, where all files are
	 * accounted for.
	 */
	public static boolean areContentsEqual(Path path1, Path path2) {
		return areContentsEqual(path1, path2, null, null);
	}

	/**
	 * Recursively checks the equality of the given files / directories, based on
	 * their effective content (i.e. the files/sub-directories they contain and the
	 * contents of those files without whitespaces).<br>
	 * <br>
	 * <b><i>The provided inclusion and exclusion pattern sets are assumed to
	 * contain no mutual elements. If they do, exclusion will take
	 * precedence.</i></b> <br>
	 * <br>
	 * Whether files are accounted for will be determined based on the given
	 * inclusion and exclusion patterns:
	 * <ul>
	 * <li>includedFilePatterns and excludedFilePatterns have elements: Each file
	 * whose full path matches ANY pattern in includedFilePatterns AND matches NO
	 * pattern in excludedFilePatterns is included.
	 * <li>includedFilePatterns has no elements but excludedFilePatterns does: Each
	 * file whose full path matches NO exclusion pattern is included.
	 * <li>excludedFilePatterns has no elements but includedFilePatterns does: Each
	 * file whose full path matches ANY inclusion pattern is included.
	 * <li>includedFilePatterns and excludedFilePatterns have no elements: All files
	 * are included.
	 * </ul>
	 * 
	 * @param path1                A given path to a file or directory
	 * @param path2                Another given path to a file or directory
	 * @param includedFilePatterns Set of file path patterns (as regex) to account
	 *                             for. If null, it will be assumed to be empty.
	 * @param excludedFilePatterns Set of file path patterns (as regex) to ignore.
	 *                             If null, it will be assumed to be empty.
	 * 
	 * @return Whether contents under the given paths are equal with respect to
	 *         {@link #readEffectiveText(File)}. Returns false, if any exception
	 *         occurs while trying to access a relevant file.
	 * @see {@link #filesEqual(File, File)}, {@link #readEffectiveText(File)}
	 */
	public static boolean areContentsEqual(Path path1, Path path2, Set<String> includedFilePatterns,
			Set<String> excludedFilePatterns) {
		final Set<Pattern> includePatterns = includedFilePatterns != null
				? includedFilePatterns.stream().map((p) -> Pattern.compile(p)).collect(Collectors.toUnmodifiableSet())
				: Set.of();
		final Set<Pattern> excludePatterns = excludedFilePatterns != null
				? excludedFilePatterns.stream().map((p) -> Pattern.compile(p)).collect(Collectors.toUnmodifiableSet())
				: Set.of();
		final var fileFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				var pathString = dir != null ? dir.toPath().resolve(name).toString() : name;
				return (includePatterns.isEmpty()
						|| includePatterns.stream().anyMatch((ip) -> ip.matcher(pathString).matches()))
						&& excludePatterns.stream().noneMatch((ep) -> ep.matcher(pathString).matches());
			}
		};

		final var result = new boolean[] { true };
		try {
			Files.walkFileTree(path1, new FileVisitor<Path>() {
				private Path getPath2Correspondent(Path currentPath) {
					return path2.resolve(path1.relativize(currentPath));
				}

				private Path getPath1Correspondent(Path currentPath) {
					return path1.resolve(path2.relativize(currentPath));
				}

				private FileVisitResult terminateWalk(File pathInPath1, File pathInPath2) {
					result[0] = false;
					SimilarityTestLogger.logDebugMsg(
							String.format("%s exists yet %s does not", pathInPath1.toString(), pathInPath2.toString()),
							FileUtil.class);
					return FileVisitResult.TERMINATE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					var path1Files = dir.toFile().listFiles(fileFilter);
					var path2Dir = this.getPath2Correspondent(dir);
					var path2Files = path2Dir.toFile().listFiles(fileFilter);

					// Check if all relevant files are present on both sides
					// Important since path2 is not automatically accounted for here
					var pf1Set = Set.of(path1Files);
					var pf2Set = Set.of(path2Files);
					// Make sure to remove the files from the opposite sides
					for (var pf1 : path1Files) {
						pf2Set.remove(this.getPath2Correspondent(pf1.toPath()).toFile());
					}
					for (var pf2 : path2Files) {
						pf1Set.remove(this.getPath1Correspondent(pf2.toPath()).toFile());
					}
					if (!pf1Set.isEmpty() || !pf2Set.isEmpty()) {
						result[0] = false;
						SimilarityTestLogger.logDebugMsg(
								String.format("%s (%s) and %s (%s) have different relevant files", dir.toString(),
										pf1Set.toString(), path2Dir.toString(), pf2Set.toString()),
								FileUtil.class);
						return FileVisitResult.TERMINATE;

					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					var parentFile = file.getParent() != null ? file.getParent().toFile() : null;
					if (fileFilter.accept(parentFile, file.getFileName().toString())) {
						var correspondent = this.getPath2Correspondent(file);
						if (!correspondent.toFile().exists() || !filesEqual(file.toFile(), correspondent.toFile())) {
							return this.terminateWalk(file.toFile(), correspondent.toFile());
						}
					}
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
					var parentFile = file.getParent() != null ? file.getParent().toFile() : null;
					if (fileFilter.accept(parentFile, file.getFileName().toString())) {
						SimilarityTestLogger.logDebugMsg(String.format("Could not visit %s", file.toString()),
								FileUtil.class);
						throw exc;
					} else {
						return FileVisitResult.CONTINUE;
					}
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc != null) {
						SimilarityTestLogger.logDebugMsg(String.format("Could not visit %s", dir.toString()),
								FileUtil.class);
						throw exc;
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			SimilarityTestLogger.logErrorMsg(
					String.format("Exception occurred while comparing file contents: %s", e.getMessage()),
					FileUtil.class);
			return false;
		}
		return result[0];
	}

	/**
	 * Reads the given file and removes line breaks and whitespaces. <br>
	 * <br>
	 * If the given file cannot be read (due to IOException), returns an empty
	 * string.
	 */
	public static String readEffectiveText(File f) {
		var content = "";

		try {
			content = Files.readString(f.toPath());
		} catch (IOException e) {
			SimilarityTestLogger.logErrorMsg(
					String.format("Could not read: %s, returning empty string", f.toPath().toString()), FileUtil.class);
		}

		return content.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\s", "");
	}

	/**
	 * Compares the equality of the given files based on their effective content,
	 * i.e. their content without whitespaces. <br>
	 * <br>
	 * If both files cannot be read, they are ignored and this method returns true.
	 * 
	 * @see {@link #readEffectiveText(File)}
	 */
	public static boolean filesEqual(File f1, File f2) {
		var f1Content = readEffectiveText(f1);
		var f2Content = readEffectiveText(f2);

		if (f1Content.isBlank() && f2Content.isBlank()) {
			return true;
		}

		return f1Content.equals(f2Content);
	}

	/**
	 * Recursively cleans files. If a file or directory cannot be deleted, requests
	 * its deletion upon termination of JVM.
	 * 
	 * @param file The file or directory to delete
	 * @see {@link File#deleteOnExit()}
	 */
	public static void deleteAll(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				var children = file.listFiles();

				if (children != null) {
					for (var cf : children) {
						deleteAll(cf);
					}
				}
			}

			if (!file.delete()) {
				file.deleteOnExit();
			}
		}
	}

	/**
	 * A variant of {@link #deleteAll(File)} that converts the given path to a file.
	 */
	public static void deleteAll(Path path) {
		deleteAll(path.toFile());
	}
}
