package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.AbstractJaMoPPParserRepoUtilTest;

public class CommentRemoverMultiLineCommentTest extends AbstractJaMoPPParserRepoUtilTest {
	@Test
	public void removeBlockComment_SingleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "/* abc */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = "/* abc */" + code;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_SurroundingCode() {
		var cr = new CommentRemoverLexer();

		var code1 = "def ";
		var code2 = "hgf ";
		var line1 = code1 + "/* abc */" + code2;

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_SingleLine_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/* abc */";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/* abc */";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	@Test
	public void removeBlockComment_MultipleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "/*";
		var line2 = "abc */";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_MultipleLine_FollowingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = "/* abc ";
		var line2 = "*/" + code;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_MultipleLine_SurroundingCode() {
		var cr = new CommentRemoverLexer();

		var code1 = "def ";
		var code2 = "hgf ";

		var line1 = code1 + "/* ";
		var line2 = "abc */" + code2;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		// New line was the part of the commentary
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_MultipleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* ";
		var line2 = "abc";
		var line3 = "*/";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleLine_NoContext_WithStarInBody() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = " * abc";
		var line3 = " */";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleLine_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/* ";
		var line3 = "abc";
		var line4 = "*/";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_MultipleLine_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = "abc";
		var line3 = "*/";
		var line4 = "def ";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line4, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_MultipleLine_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/*";
		var line3 = "abc";
		var line4 = " */";
		var line5 = "hgf ";

		var text = concatLines(line1, line2, line3, line4, line5);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line5, filteredText.get(1));
	}

	@Test
	public void removeBlockComment_MultipleComments_BothSingleLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";
		var line2 = "/* def */";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_BothInSameLineNoSpace() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc *//* def */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_BothInSameLineWithSpace() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */ /* def */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_OneSingleLineOneMultipleLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";
		var line2 = "/*";
		var line3 = "def";
		var line4 = " */";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_OneSingleLineOneMultipleLine_NoSpace() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc *//*";
		var line2 = "def";
		var line3 = " */";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_OneSingleLineOneMultipleLine_WithSpace() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */ /*";
		var line2 = "def";
		var line3 = " */";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleComments_BothMultipleLine() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = "abc";
		var line3 = " */";
		var line4 = "/*";
		var line5 = "def";
		var line6 = " */";

		var text = concatLines(line1, line2, line3, line4, line5, line6);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_SingleLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* \"abc\" */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* \"\"\"abc\"\"\" */";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral_Split() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* \"\"\"abc";
		var line2 = "\"\"\" */";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}
}
