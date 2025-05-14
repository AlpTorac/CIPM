package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.AbstractJaMoPPParserRepoUtilTest;

public class CommentRemoverSingleLineStringTest extends AbstractJaMoPPParserRepoUtilTest {
	@Test
	public void handleStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_WithSingleEscapedQuotation() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\\\"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_WithMultipleEscapedQuotations() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\\\"abc\\\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleSingleLineCommentInStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"// abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleMultiLineCommentInStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"/* abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleJavaDocInStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"/** abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleSingleLineCommentInStringLiteral_WithEscapedQuotation() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\\\"// abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleMultiLineCommentInStringLiteral_WithEscapedQuotation() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\\\"/* abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleJavaDocInStringLiteral_WithEscapedQuotation() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\\\"/** abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithSingleLineComment() {
		var cr = new CommentRemoverLexer();

		var start = "\"";
		var line1 = start + "//abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithMultiLineComment() {
		var cr = new CommentRemoverLexer();

		var start = "\"";
		var line1 = start + "/*abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithJavaDoc() {
		var cr = new CommentRemoverLexer();

		var start = "\"";
		var line1 = start + "/**abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}
}
