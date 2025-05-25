package cipm.consistency.fitests.repositorytests.util.commentremoval;

/**
 * A comment remover that gives precedence to block-comment tokens (such as
 * {@code /*, * /}) over string tokens (i.e. {@code " and """}), unless the
 * comment is guaranteed to be a part of a string literal. That means, all
 * potentially broken block-comments are removed, even if they are a part of a
 * string literal in reality. <b><i>The comment removal offered by this class is
 * an approximation</i></b>.
 * 
 * @author Alp Torac Genc
 */
public class QuickCommentRemover implements ICommentRemover {

	// TODO Use Pattern and Matcher to optimise

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
	 * of the string literal. Exclude escaped quotation marks (i.e. {@code \"}) in
	 * the process. <br>
	 * <br>
	 * <b><i>Does not account for potential multi-line string declarations
	 * {@code """..."""}. Multi-line string declarations are handled, as if they
	 * were consecutive single line string literals</i></b>. This means, multi-line
	 * strings that are declared on the same line are still detected as string
	 * literals. However, multi-line strings that are declared over multiple lines
	 * are detected as faulty string literals, i.e. this method returns -1.
	 * 
	 * @param quotationIdx The index of the quotation mark, which starts the string
	 *                     literal. In {@code "abc"}, it is 0.
	 * @param text         The text that should be analysed for single line string
	 *                     literals
	 * @return The first index at the end of the single line string literal. In
	 *         {@code "abc"}, it is 4. Returns -1 if the string literal never ends.
	 */
	private int parseSingleLineStringLiteral(int quotationIdx, String text) {
		if (text.length() <= quotationIdx + 1 || text.charAt(quotationIdx) != quotationMark ||
		// Check for escaped quotation
				(quotationIdx > 0 && text.charAt(quotationIdx - 1) == backslash)

//				||
// 				Check for multi-line string declaration
//				(text.length() >= quotationIdx + multiLineStringToken.length()
//						&& text.substring(quotationIdx, quotationIdx + multiLineStringToken.length())
//								.equals(multiLineStringToken))

		)
			// quotationIdx does not mark the start of a single line string literal
			return -1;

		/*
		 * Skip quotationIdx, since the starting and ending tokens (") are the same.
		 * 
		 * Single line strings have to start and end on the same line, so only consider
		 * the char sequence between the starting quotation mark and the end of the line
		 * (either line break or end of text).
		 */

		var newLineIdx = text.indexOf(lineSeparator, quotationIdx);
		var rangeEnd = newLineIdx != -1 ? newLineIdx : text.length();

		for (int i = quotationIdx + 1; i < rangeEnd; i++) {
			if (text.charAt(i) == quotationMark && text.charAt(i - 1) != backslash) {
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
	 *         {@code """abc"""}, it is 8. Returns -1 if the string literal never
	 *         ends.
	 */
	@SuppressWarnings("unused")
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
	 * @return The first index at the end of the multi line comment. Returns -1 if
	 *         the comment never ends.
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

	public String removeComments(String text) {
		var result = "";

		var currentCharIdx = 0;
		while (currentCharIdx < text.length()) {
			var parseEndIdx = -1;

			/*
			 * Check order:
			 * 
			 * 1) Multi-line string literals (can contain tokens of comments)
			 * 
			 * 2) Single line string literals (can contain tokens of comments)
			 * 
			 * 3) Block comments (may start and end in a single line, JavaDoc included)
			 * 
			 * 4) Single line comments (can only end with the line)
			 */

//			if ((parseEndIdx = this.parseMultiLineStringLiteral(currentCharIdx, text)) != -1) {
//				result += text.substring(currentCharIdx, parseEndIdx);
//			}

			if ((parseEndIdx = this.parseSingleLineStringLiteral(currentCharIdx, text)) != -1) {
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

		if (this.hasLeadingBrokenComment(result)) {
			// There is leading broken commentary, cut it out
			var blockCommentEndIdx = result.indexOf(blockCommentEnd);
			result = result.substring(blockCommentEndIdx + blockCommentEnd.length(), result.length());
		}
		if (this.hasTrailingBrokenComment(result)) {
			// There is trailing broken commentary, cut it out
			result = result.substring(0, result.lastIndexOf(blockCommentStart));
		}

		return result;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Does not account for the broken comments to be a part of a multi-line string.
	 */
	public boolean hasLeadingBrokenComment(String text) {
		var blockCommentStartIdx = text.indexOf(blockCommentStart);
		var blockCommentEndIdx = text.indexOf(blockCommentEnd);

		return blockCommentEndIdx != -1 && (blockCommentStartIdx == -1 || blockCommentStartIdx > blockCommentEndIdx);

	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * Does not account for the broken comments to be a part of a multi-line string.
	 */
	public boolean hasTrailingBrokenComment(String text) {
		var blockCommentStartIdx = text.lastIndexOf(blockCommentStart);
		var blockCommentEndIdx = text.lastIndexOf(blockCommentEnd);

		return blockCommentStartIdx != -1 && (blockCommentEndIdx == -1 || blockCommentStartIdx > blockCommentEndIdx);
	}
}
