package cipm.consistency.fitests.repositorytests;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CommentRemover {
	private List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		var diffLines = diff.split(System.lineSeparator());

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}

	public List<String> removeCommentary(String diff) {
		var lines = this.splitLines(diff);
		var linesWithoutCommentary = new ArrayList<String>();

		/*
		 * TODO: Use Matcher and Pattern rather than String.indexOf (?)
		 * 
		 * TODO: Account for multiple comments on a single line
		 * ("abc /* c * / def /** d * / hgf // xyz")
		 * 
		 * TODO: Account for commentary tokens being a string literal rather than
		 * commentary
		 * 
		 * Make sure that the quotations (") are not escaped (\")
		 * 
		 * Important because of queries, meta-programming, etc.
		 */

		var inBlockComment = false; // Whether the current line is a part of a block comment

		for (var l : lines) {
			var singleLineCommentIdx = l.indexOf("//");
			var blockCommentStartIdx = l.indexOf("/*"); // Includes JavaDoc
			var blockCommentEndIdx = l.indexOf("*/"); // Includes JavaDoc

			if (!inBlockComment) {
				if (singleLineCommentIdx != -1) {

					// Exclude empty/blank strings
					if (singleLineCommentIdx > 0) {
						linesWithoutCommentary.add(l.substring(0, singleLineCommentIdx));
					}
					continue;
				}

				if (blockCommentStartIdx != -1 && blockCommentEndIdx != -1) {
					var lineToAdd = "";

					// Exclude empty/blank strings
					if (blockCommentStartIdx > 0) {
						lineToAdd = l.substring(0, blockCommentStartIdx);
					}
					if (blockCommentEndIdx + 2 < l.length() - 1) {
						lineToAdd += l.substring(blockCommentEndIdx + 2, l.length());
					}
					if (!lineToAdd.isBlank())
						linesWithoutCommentary.add(lineToAdd);

					continue;
				} else if (blockCommentStartIdx != -1) {
					inBlockComment = true;
					// Exclude empty/blank strings
					if (blockCommentStartIdx > 0) {
						linesWithoutCommentary.add(l.substring(0, blockCommentStartIdx));
					}
					continue;
				}

				linesWithoutCommentary.add(l);

			} else {
				if (blockCommentEndIdx != -1) {
					inBlockComment = false;
					// Exclude empty/blank strings
					if (blockCommentEndIdx + 2 < l.length() - 1) {
						linesWithoutCommentary.add(l.substring(blockCommentEndIdx + 2, l.length()));
					}
					continue;
				}
			}
		}

		return linesWithoutCommentary;
	}
}
