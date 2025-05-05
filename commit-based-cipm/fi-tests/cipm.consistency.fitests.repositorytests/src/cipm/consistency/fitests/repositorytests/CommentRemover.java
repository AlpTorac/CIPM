package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.List;

public class CommentRemover {
	private static final String diffCommandPattern = "diff --git .*";
	private static final String diffLocationPattern = "@@ .* @@";
	private static final String diffIndexPattern = "index .*";
	private static final String diffFileAddPattern = "+++ .*\\.\\w*";
	private static final String diffFileRemovePattern = "--- .*\\.\\w*";

	private final List<String> addLines = new ArrayList<String>();
	private final List<String> removeLines = new ArrayList<String>();
	private final List<String> contextLines = new ArrayList<String>();

	public boolean isContentLine(String line) {
		if (line.matches(diffCommandPattern)) {
			return false;
		} else if (line.matches(diffFileAddPattern)) {
			return false;
		} else if (line.matches(diffFileRemovePattern)) {
			return false;
		} else if (line.matches(diffLocationPattern)) {
			return false;
		} else if (line.matches(diffIndexPattern)) {
			return false;
		} else {
			return true;
		}
	}

	private void splitGroups(String diff) {
		var diffLines = diff.split("\\n");
		if (diffLines.length > 0) {
			addLines.clear();
			removeLines.clear();
			contextLines.clear();
		}

		for (var l : diffLines) {
			// Skip file names and comments
			if (!isContentLine(l)) {
				continue;
			}

			if (l.startsWith("+")) {
				addLines.add(l.substring(1));
			} else if (l.startsWith("-")) {
				removeLines.add(l.substring(1));
			} else {
				contextLines.add(l.substring(1));
			}
		}
	}

	private String[] removeIrrelevantLines(String diff) {
		var relevantLines = new ArrayList<String>();

		var diffLines = diff.split("\\n");

		for (var l : diffLines) {
			if (isContentLine(l)) {
				relevantLines.add(l);
			}
		}

		return relevantLines.toArray(String[]::new);
	}

	private List<String> removeCommentary(String diff) {
		var lines = this.removeIrrelevantLines(diff);
		var linesWithoutCommentary = new ArrayList<String>();

		var inBlockComment = false; // Whether the current line is a part of a block comment

		for (var l : lines) {
			var singleLineCommentIdx = l.indexOf("//");
			var blockCommentStartIdx = l.indexOf("/*"); // Includes JavaDoc
			var blockCommentEndIdx = l.indexOf("*/"); // Includes JavaDoc

			// TODO: Account for "//" being a string rather than commentary
			if (!inBlockComment) {
				if (singleLineCommentIdx != -1) {
					linesWithoutCommentary.add(l.substring(0, singleLineCommentIdx));
					continue;
				}

				if (blockCommentStartIdx != -1 && blockCommentEndIdx != 1) {
					linesWithoutCommentary.add(
							l.substring(0, blockCommentStartIdx) + l.substring(blockCommentEndIdx + 2, l.length()));
					continue;
				}

				if (blockCommentStartIdx != -1) {
					inBlockComment = true;
					linesWithoutCommentary.add(l.substring(0, blockCommentStartIdx));
					continue;
				}
			} else {
				if (blockCommentEndIdx != -1) {
					linesWithoutCommentary.add(l.substring(blockCommentEndIdx + 2, l.length()));
					inBlockComment = false;
					continue;
				}
			}
		}

		return linesWithoutCommentary;
	}
}
