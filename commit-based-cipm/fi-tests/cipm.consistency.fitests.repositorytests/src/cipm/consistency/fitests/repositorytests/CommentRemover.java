package cipm.consistency.fitests.repositorytests;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CommentRemover {
	private static final String diffCommandPattern = "diff --git .*";
	private static final String diffLocationPattern = "@@ .* @@";
	private static final String diffIndexPattern = "index .*";
	private static final String diffFileAddPattern = "\\+\\+\\+ .*\\.\\w*";
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

	private List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		var diffLines = diff.split(System.lineSeparator());

		for (var l : diffLines) {
			lines.add(l);
		}
		
		return lines;
	}
	
	private List<String> removeIrrelevantLines(String diff) {
		var lines = this.splitLines(diff);
		lines.removeIf((l) -> !isContentLine(l));
		return lines;
	}

	public List<String> removeCommentary(String diff) {
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
					if (singleLineCommentIdx > 0) {
						linesWithoutCommentary.add(l.substring(0, singleLineCommentIdx));
					}
					continue;
				}

				if (blockCommentStartIdx != -1 && blockCommentEndIdx != -1) {
					if (blockCommentStartIdx > 0 || blockCommentEndIdx + 2 < l.length()) {
						linesWithoutCommentary.add(
								l.substring(0, blockCommentStartIdx) + l.substring(blockCommentEndIdx + 2, l.length()));
					}
					continue;
				}

				if (blockCommentStartIdx != -1) {
					inBlockComment = true;
					if (blockCommentStartIdx > 0) {
						linesWithoutCommentary.add(l.substring(0, blockCommentStartIdx));
					}
					continue;
				}
			} else {
				if (blockCommentEndIdx != -1) {
					inBlockComment = false;
					if (blockCommentEndIdx + 2 < l.length()) {
						linesWithoutCommentary.add(l.substring(blockCommentEndIdx + 2, l.length()));
					}
					continue;
				}
			}
			linesWithoutCommentary.add(l);
		}

		return linesWithoutCommentary;
	}
}
