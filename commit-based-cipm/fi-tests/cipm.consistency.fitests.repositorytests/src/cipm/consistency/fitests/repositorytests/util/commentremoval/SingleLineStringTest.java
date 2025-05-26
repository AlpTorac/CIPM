package cipm.consistency.fitests.repositorytests.util.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.util.AbstractJaMoPPParserRepoUtilTest;

public class SingleLineStringTest extends AbstractJaMoPPParserRepoUtilTest {
	private ICommentRemover cr = new QuickCommentRemover();

	@Test
	public void handleStringLiteral() {
		var line1 = "\"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_WithSingleEscapedQuotation() {
		var line1 = "\"\\\"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_WithMultipleEscapedQuotations() {
		var line1 = "\"\\\"abc\\\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_MultiLineStringAsSingleLineString() {
		var line1 = "\"\"\"//abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleSingleLineCommentInStringLiteral() {
		var line1 = "\"// abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleMultiLineCommentInStringLiteral() {
		var line1 = "\"/* abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleJavaDocInStringLiteral() {
		var line1 = "\"/** abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleSingleLineCommentInStringLiteral_WithEscapedQuotation() {
		var line1 = "\"\\\"// abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleMultiLineCommentInStringLiteral_WithEscapedQuotation() {
		var line1 = "\"\\\"/* abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleJavaDocInStringLiteral_WithEscapedQuotation() {
		var line1 = "\"\\\"/** abc */\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral() {
		var code = "\"abc";
		var comment = "//def";
		var line1 = code + comment;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithEscapedQuotationStart() {
		var code = "\\\"abc";
		var comment = "//def\"";
		var line1 = code + comment;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithEscapedQuotationEnd() {
		var code = "\"abc";
		var comment = "//def\\\"";
		var line1 = code + comment;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithSingleLineComment() {
		var start = "\"";
		var line1 = start + "//abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithMultiLineComment() {
		var start = "\"";
		var line1 = start + "/*abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithJavaDoc() {
		var start = "\"";
		var line1 = start + "/**abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleBrokenStringLiteral_SingleLineString_AcrossTwoLines() {
		var comment = "//abc";

		var line1 = "\"" + comment;
		var line2 = "\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals("\"", filteredText.get(0));
		Assertions.assertEquals("\"", filteredText.get(1));
	}

	@Test
	public void handleBrokenStringLiteral_SingleLineString_AcrossThreeLines() {
		var line1 = "\"";
		var line2 = "//abc";
		var line3 = "\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals("\"", filteredText.get(0));
		Assertions.assertEquals("\"", filteredText.get(1));
	}
}
