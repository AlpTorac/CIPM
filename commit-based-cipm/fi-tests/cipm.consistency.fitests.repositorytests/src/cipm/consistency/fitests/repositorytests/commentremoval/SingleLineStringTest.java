package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.AbstractJaMoPPParserRepoUtilTest;

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
		var line1 = "\"abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
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
}
