package cipm.consistency.fitests.repositorytests;

public class CommentRemoverLexer {
	private static final char quotationMark = '\"';
	private static final char backslash = '\\';

	private static final String doubleSlash = "//";
	private static final String multiLineStringToken = "\"\"\"";
	private static final String blockCommentStart = "/*";
	private static final String blockCommentEnd = "*/";
	private static final String lineSeparator = System.lineSeparator();

	/**
	 * Single line string literals (such as {@code "abc"}) cannot be nested. Start
	 * from quotationIdx and look for the next quotation mark, which marks the end
	 * of the string literal. Exclude escaped quotation marks (i.e. {@code \"}) and
	 * multi-line string declarations (i.e. {@code """ ... """}) in the process.
	 * 
	 * @param quotationIdx The index of the quotation mark, which starts the string
	 *                     literal. In {@code "abc"}, it is 0.
	 * @param text         The text that should be analysed for single line string
	 *                     literals
	 * @return The first index at the end of the single line string literal. In
	 *         {@code "abc"}, it is 4.
	 */
	private int parseSingleLineStringLiteral(int quotationIdx, String text) {
		if (text.length() <= quotationIdx + 1 || text.charAt(quotationIdx) != quotationMark ||
		// Check for escaped quotation
				(quotationIdx > 0 && text.charAt(quotationIdx - 1) == backslash) ||
				// Check for multi-line string declaration
				(text.length() >= quotationIdx + multiLineStringToken.length()
						&& text.substring(quotationIdx, quotationIdx + multiLineStringToken.length())
								.equals(multiLineStringToken)))
			// quotationIdx does not mark the start of a single line string literal
			return -1;

		// Skip quotationIdx, since the starting and ending tokens (") are the same
		for (int i = quotationIdx + 1; i < text.length(); i++) {
			if (text.charAt(i) == quotationMark) {
				// End of the string literal found, return index after closing quotation mark
				return i + 1;
			}
		}

		// Single line string literal is never closed => Problem with text
		return -1;
	}

	/**
	 * Multi line string literals (such as {@code """abc"""}) cannot be nested.
	 * Start from quotationIdx and look for the end of the string literal.
	 * 
	 * @param quotationIdx The starting index of the multi line string token
	 *                     {@code """}, which starts the string literal. In
	 *                     {@code """abc"""}, it is 0.
	 * @param text         The text that should be analysed for multi line string
	 *                     literals
	 * @return The first index at the end of the multi line string literal. . In
	 *         {@code """abc"""}, it is 8.
	 */
	private int parseMultiLineStringLiteral(int quotationIdx, String text) {
		var mlstLen = multiLineStringToken.length();

		// Make sure that there are enough characters left for a multi-line string
		if (text.length() < quotationIdx + mlstLen
				|| !text.substring(quotationIdx, quotationIdx + mlstLen).equals(multiLineStringToken))
			// quotationIdx does not mark the start of a multi line string literal
			return -1;

		// Skip quotationIdx, since the starting and ending tokens (""") are the same
		for (int i = quotationIdx + mlstLen; i <= text.length() - mlstLen; i++) {
			if (text.substring(i, i + mlstLen).equals(multiLineStringToken)) {
				// End of the multi-line string found, return index after multiLineStringToken
				// (""")
				return i + mlstLen;
			}
		}

		// Multi line string literal is never closed => Problem with text
		return -1;
	}

	/**
	 * Single line comments (such as {@code // abc}) cannot be nested. Start from
	 * doubleSlashIdx and look for the end of the line.
	 * 
	 * @param doubleSlashIdx The starting index of the single line comment
	 * @param text           The text that should be analysed for single line
	 *                       comments
	 * @return The first index at the end of the single line comment, i.e. the
	 *         beginning of the next line.
	 */
	private int parseSingleLineComment(int doubleSlashIdx, String text) {
		var lineSepLen = lineSeparator.length();
		if (text.length() < doubleSlashIdx + lineSepLen
				|| !text.substring(doubleSlashIdx, doubleSlashIdx + lineSepLen).equals(doubleSlash)) {
			return -1;
		} else {
			for (int i = doubleSlashIdx; i <= text.length() - lineSepLen; i++) {
				// Look for the closest line break
				if (text.substring(i, i + lineSepLen).equals(lineSeparator)) {
					// Line break found, return index after comment but before line break
					return i;
				}
			}

			// No line break found, single line comment goes till the end of the text
			return text.length();
		}
	}

	/**
	 * Multi line comments (such as {@code // abc}) cannot be nested. Start from
	 * slashStarIdx and look for the end of the multi line comment. <br>
	 * <br>
	 * Accounts for both multi line comments (i.e. {@code /* ... * /} ) and JavaDoc
	 * (i.e. {@code /** .... * /}).
	 * 
	 * @param slashStarIdx The starting index of the multi line comment
	 * @param text         The text that should be analysed for multi line comments
	 * @return The first index at the end of the multi line comment.
	 */
	private int parseBlockComment(int slashStarIdx, String text) {
		var commentEndLen = blockCommentEnd.length(); // Length of "*/"
		if (text.length() < slashStarIdx + commentEndLen
				|| !text.substring(slashStarIdx, slashStarIdx + commentEndLen).equals(blockCommentStart)) {
			return -1;
		} else {
			for (int i = slashStarIdx; i <= text.length() - commentEndLen; i++) {
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

			if ((parseEndIdx = this.parseSingleLineStringLiteral(currentCharIdx, text)) != -1) {
				result += text.substring(currentCharIdx, parseEndIdx);
			} else if ((parseEndIdx = this.parseMultiLineStringLiteral(currentCharIdx, text)) != -1) {
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
