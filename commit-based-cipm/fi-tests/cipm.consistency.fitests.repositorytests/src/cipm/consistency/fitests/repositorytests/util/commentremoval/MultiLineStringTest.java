package cipm.consistency.fitests.repositorytests.util.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.util.AbstractJaMoPPParserRepoUtilTest;

public class MultiLineStringTest extends AbstractJaMoPPParserRepoUtilTest {
	private ICommentRemover cr = new QuickCommentRemover();

	@Test
	public void handleStringLiteral_SingleLineString_OnSameLine() {
		var line1 = "\"\"\"abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineString_StartAndStringOnSameLine() {
		var line1 = "\"\"\"abc";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineString_EndAndStringOnSameLine() {
		var line1 = "\"\"\"";
		var line2 = "abc\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineString_SurroundingStartAndEnd() {
		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_OnSameLine() {
		var line1 = "\"\"\"abc";
		var line2 = "def\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineString_StartAndStringOnSameLine() {
		var line1 = "\"\"\"abc";
		var line2 = "def";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_EndAndStringOnSameLine() {
		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "def\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_SurroundingStartAndEnd() {
		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "def";
		var line4 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(4, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
	}

	@Test
	public void handleUnclosedMultiLineStringLiteral() {
		var start = "\"\"\"";
		var line1 = start + "abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedMultiLineStringLiteral_FaultyEnd() {
		var start = "\"\"\"";
		var end = "\"\"";
		var line1 = start + "abc" + end;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithSingleLineComment() {
		var start = "\"\"\"";
		var line1 = start + "//abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithMultiLineComment() {
		var start = "\"\"\"";
		var line1 = start + "/*abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithJavaDoc() {
		var start = "\"\"\"";
		var line1 = start + "/**abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_OnSameLine() {
		var line1 = "\"\"\"//abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_StartAndStringOnSameLine() {
		var line1 = "\"\"\"//abc";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_EndAndStringOnSameLine() {
		var line1 = "\"\"\"";
		var line2 = "//abc\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_SurroundingStartAndEnd() {
		var line1 = "\"\"\"";
		var line2 = "//abc";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_OnSameLine() {
		var line1 = "\"\"\"/*abc*/\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_StartAndStringOnSameLine() {
		var line1 = "\"\"\"/*abc*/";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_EndAndStringOnSameLine() {
		var line1 = "\"\"\"";
		var line2 = "/*abc*/\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_SurroundingStartAndEnd() {
		var line1 = "\"\"\"";
		var line2 = "/*abc*/";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_JavaDoc_OnSameLine() {
		var line1 = "\"\"\"/**abc*/\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_JavaDoc_StartAndStringOnSameLine() {
		var line1 = "\"\"\"/**abc*/";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_EndAndStringOnSameLine() {
		var line1 = "\"\"\"";
		var line2 = "/**abc*/\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_SurroundingStartAndEnd() {
		var line1 = "\"\"\"";
		var line2 = "/**abc*/";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}
}
