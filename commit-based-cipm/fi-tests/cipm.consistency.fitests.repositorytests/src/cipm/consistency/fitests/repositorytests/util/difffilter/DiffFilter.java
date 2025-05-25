package cipm.consistency.fitests.repositorytests.util.difffilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DiffFilter {
	private static final Pattern diffLineSignPattern = Pattern.compile("^(?:\\+|-)");
	private static final Pattern diffLineWhitespacePattern = Pattern
			.compile(String.format("%s?\\s*$", diffLineSignPattern.pattern()));

	private static final Pattern diffHeaderPattern = Pattern.compile("^\\s*diff --git .*");
	private static final Pattern diffHunkHeaderPattern = Pattern.compile("^@@ .* @@$");

	private static final Pattern diffFileMetadataPattern = Pattern.compile("^index .*");

	private static final Pattern diffModePattern = Pattern.compile("^(?:old|new|new file|deleted file) mode .*");
	private static final Pattern diffRenamePattern = Pattern.compile("^rename (?:to|from) .*\\.\\w*");
	private static final Pattern diffCopyPattern = Pattern.compile("^copy (?:to|from) .*\\.\\w*");

	private static final Pattern diffNewFileStatePattern = Pattern.compile("^\\+\\+\\+ (?:.*\\.\\w*|/.*)");
	private static final Pattern diffOldFileStatePattern = Pattern.compile("^--- (?:.*\\.\\w*|/.*)");

	private static final Pattern diffSimilarityIndexPattern = Pattern.compile("^similarity index \\d*\\.?\\d+%");
	private static final Pattern diffDissimilarityIndexPattern = Pattern.compile("^dissimilarity index \\d*\\.?\\d+%");

	/*
	 * TODO Find out why using "^\\ No newline at end of file$" as a pattern does
	 * not work
	 */
	private static final String diffNoNewLineMessage = "\\ No newline at end of file";

	private static final String unixNewLine = "\\n";

	/**
	 * A variant of {@link #splitLines(String, String)} that uses the new line
	 * character for UNIX ( {@code "\\n"} ). Implemented as a convenience method,
	 * since GIT internally uses a UNIX terminal.
	 */
	public List<String> splitLines(String diff) {
		return splitLines(diff, unixNewLine);
	}

	public List<String> splitLines(String diff, String lineSeparator) {
		var lines = new ArrayList<String>();

		// Do not use System.lineSeparator since GIT uses UNIX terminal
		// UNIX terminal uses "\n" for new line
		var diffLines = diff.split(lineSeparator);

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}

	public List<String> filterIrrelevantLines(List<String> lines) {
		lines.removeIf((l) -> !isContentLine(l));
		return lines;
	}

	/**
	 * A variant of {@link #filterIrrelevantLines(String, String)} that uses the new
	 * line character for UNIX ( {@code "\\n"} ). Implemented as a convenience
	 * method, since GIT internally uses a UNIX terminal.
	 */
	public List<String> filterIrrelevantLines(String diff) {
		return filterIrrelevantLines(diff, unixNewLine);
	}

	public List<String> filterIrrelevantLines(String diff, String lineSeparator) {
		var lines = this.splitLines(diff, lineSeparator);
		return filterIrrelevantLines(lines);
	}

	public List<String> removeBlankLines(List<String> lines) {
		lines.removeIf((l) -> diffLineWhitespacePattern.matcher(l).matches());
		return lines;
	}

	public List<String> removeContextLines(List<String> lines) {
		lines.removeIf((l) -> !diffLineSignPattern.matcher(l).find());
		return lines;
	}

	public boolean isContentLine(String line) {
		if (diffHeaderPattern.matcher(line).find()) {
			return false;
		} else if (diffNewFileStatePattern.matcher(line).find()) {
			return false;
		} else if (diffOldFileStatePattern.matcher(line).find()) {
			return false;
		} else if (diffFileMetadataPattern.matcher(line).find()) {
			return false;
		} else if (diffHunkHeaderPattern.matcher(line).find()) {
			return false;
		} else if (diffModePattern.matcher(line).find()) {
			return false;
		} else if (diffSimilarityIndexPattern.matcher(line).find()) {
			return false;
		} else if (diffCopyPattern.matcher(line).find()) {
			return false;
		} else if (diffDissimilarityIndexPattern.matcher(line).find()) {
			return false;
		} else if (diffRenamePattern.matcher(line).find()) {
			return false;
		} else if (line.equals(diffNoNewLineMessage)) {
			return false;
		} else {
			return true;
		}
	}
}
