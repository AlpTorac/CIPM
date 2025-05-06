package cipm.consistency.fitests.repositorytests;

public class CommentRemoverLexer {
	private static final char quotationMark = '\"';
	private static final char backslash = '\\';

	private static final String doubleSlash = "//";
	private static final String blockCommentStart = "/*";
	private static final String blockCommentEnd = "*/";
	private static final String lineSeparator = System.lineSeparator();

	private int parseStringLiteral(int quotationIdx, String text) {
		if (text.length() <= quotationIdx || text.charAt(quotationIdx) != quotationMark ||
		// Check for escaped quotation
				(quotationIdx > 0 && text.charAt(quotationIdx - 1) == backslash))
			// quotationIdx is not on a quotation mark
			return -1;

		for (int i = quotationIdx; i < text.length(); i++) {
			if (text.charAt(i) == quotationMark) {
				// Quotation found, return index after closing quotation mark
				return i + 1;
			}
		}

		// Quotation is never closed => Problem with text
		return -1;
	}

	private int parseSingleLineComment(int doubleSlashIdx, String text) {
		var lineSepLen = lineSeparator.length();
		if (text.length() <= doubleSlashIdx + lineSepLen
				|| !text.substring(doubleSlashIdx, doubleSlashIdx + lineSepLen).equals(doubleSlash)) {
			return -1;
		} else {
			for (int i = doubleSlashIdx; i < text.length() - lineSepLen; i++) {
				// Look for the closest line break
				if (text.substring(i, i + lineSepLen + 1).equals(lineSeparator)) {
					// Line break found, return index after comment but before line break
					return i;
				}
			}

			// No line break found, single line comment goes till the end of the text
			return text.length();
		}
	}

	private int parseBlockComment(int slashStarIdx, String text) {
		var commentEndLen = blockCommentEnd.length(); // Length of "*/"
		if (text.length() <= slashStarIdx + commentEndLen
				|| !text.substring(slashStarIdx, slashStarIdx + commentEndLen).equals(blockCommentStart)) {
			return -1;
		} else {
			for (int i = slashStarIdx; i < text.length() - commentEndLen; i++) {
				// Look for the closest comment end
				if (text.substring(i, i + commentEndLen).equals(blockCommentEnd)) {
					// Comment end found, return index after comment
					return i + commentEndLen;
				}
			}

			// Block comment never closed => Problem with text
			return -1;
		}
	}

	public String removeCommentary(String text) {
		var result = "";

		var currentCharIdx = 0;
		while (currentCharIdx < text.length()) {
			var parseEndIdx = -1;

			/*
			 * Check order:
			 * 
			 * 1) String literals (can contain tokens of others)
			 * 
			 * 2) Block comments (may start and end in a single line, JavaDoc included)
			 * 
			 * 3) Single line comments (can only end with the line)
			 */

			if ((parseEndIdx = this.parseStringLiteral(currentCharIdx, text)) != -1) {
				result += text.substring(currentCharIdx, parseEndIdx);
			} else if ((parseEndIdx = this.parseBlockComment(currentCharIdx, text)) != -1) {
			} else if ((parseEndIdx = this.parseSingleLineComment(currentCharIdx, text)) != -1) {
			} else {
				result += text.charAt(currentCharIdx);
			}

			if (parseEndIdx != -1) {
				currentCharIdx = parseEndIdx;
			} else {
				currentCharIdx++;
			}
		}

		return result;
	}
}
