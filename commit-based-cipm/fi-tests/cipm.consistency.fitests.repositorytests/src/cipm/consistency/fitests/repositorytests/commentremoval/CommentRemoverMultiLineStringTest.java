package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentRemoverMultiLineStringTest extends CommentRemoverTest {
	@Test
	public void handleStringLiteral_SingleLineString_OnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineString_StartAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"abc";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineString_EndAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineString_SurroundingStartAndEnd() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_OnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"abc";
		var line2 = "def\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineString_StartAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"abc";
		var line2 = "def";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_EndAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "def\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineString_SurroundingStartAndEnd() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "def";
		var line4 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(4, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
	}

	@Test
	public void handleUnclosedMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var start = "\"\"\"";
		var line1 = start + "abc";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedMultiLineStringLiteral_FaultyEnd() {
		var cr = new CommentRemoverLexer();

		var start = "\"\"\"";
		var end = "\"\"";
		var line1 = start + "abc" + end;

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithSingleLineComment() {
		var cr = new CommentRemoverLexer();

		var start = "\"\"\"";
		var line1 = start + "//abc";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithMultiLineComment() {
		var cr = new CommentRemoverLexer();

		var start = "\"\"\"";
		var line1 = start + "/*abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleUnclosedStringLiteral_WithJavaDoc() {
		var cr = new CommentRemoverLexer();

		var start = "\"\"\"";
		var line1 = start + "/**abc*/";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(start, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_OnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"//abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_StartAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"//abc";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_EndAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "//abc\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_SurroundingStartAndEnd() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "//abc";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_OnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"/*abc*/\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_StartAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"/*abc*/";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_EndAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/*abc*/\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_SurroundingStartAndEnd() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/*abc*/";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}

	@Test
	public void handleStringLiteral_JavaDoc_OnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"/**abc*/\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_JavaDoc_StartAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"/**abc*/";
		var line2 = "\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_EndAndStringOnSameLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/**abc*/\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_SurroundingStartAndEnd() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/**abc*/";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(3, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
	}
}
