package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.AbstractJaMoPPParserRepoUtilTest;

public class CommentRemoverBrokenCommentTest extends AbstractJaMoPPParserRepoUtilTest {
	@Test
	public void handleBlockComment_LeadingBrokenComment() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingBrokenComment_BeforeMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";
		var line3 = "\"\"\"";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingBrokenComment_AfterMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "\"\"\"";
		var line3 = "*/";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}
	
	@Test
	public void handleBlockComment_LeadingBrokenComment_AfterFullMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "\"\"\"";
		var line4 = "*/";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingBrokenComment_InMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "\"\"\"";
		var line3 = "*/";
		var line4 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(4, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingBrokenComment_MultiLineStringTokenCheck() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "abc";
		var line3 = "\"\"\"";
		var line4 = "*/";
		var line5 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4, line5);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line5, filteredText.get(0));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = "abc";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment_BeforeMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = "\"\"\"";
		var line3 = "abc";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment_BeforeFullMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = "\"\"\"";
		var line3 = "abc";
		var line4 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment_AfterMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/*";
		var line3 = "abc";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment_InMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/*";
		var line3 = "\"\"\"";
		var line4 = "abc";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(4, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_TrailingBrokenComment_MultiLineStringLiteralTokenCheck() {
		var cr = new CommentRemoverLexer();

		var line1 = "\"\"\"";
		var line2 = "/*";
		var line3 = "\"\"\"";
		var line4 = "abc";
		var line5 = "\"\"\"";

		var text = concatLines(line1, line2, line3, line4, line5);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(5, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
		Assertions.assertEquals(line5, filteredText.get(4));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";
		var line3 = "def";
		var line4 = "/*";
		var line5 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_BeforeMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";
		var line3 = "def";
		var line4 = "/*";
		var line5 = "\"\"\"";
		var line6 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_AfterMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "\"\"\"";
		var line3 = "*/";
		var line4 = "def";
		var line5 = "/*";
		var line6 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line4, filteredText.get(0));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_AroundMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";
		var line3 = "def";
		var line4 = "\"\"\"";
		var line5 = "/*";
		var line6 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
		Assertions.assertEquals(line4, filteredText.get(1));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_LeadingCommentSurroundedByMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "\"\"\"";
		var line3 = "*/";
		var line4 = "\"\"\"";
		var line5 = "def";
		var line6 = "/*";
		var line7 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6, line7);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(5, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
		Assertions.assertEquals(line5, filteredText.get(4));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertTrue(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_TrailingCommentSurroundedByMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "*/";
		var line3 = "def";
		var line4 = "\"\"\"";
		var line5 = "/*";
		var line6 = "\"\"\"";
		var line7 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6, line7);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(5, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
		Assertions.assertEquals(line4, filteredText.get(1));
		Assertions.assertEquals(line5, filteredText.get(2));
		Assertions.assertEquals(line6, filteredText.get(3));
		Assertions.assertEquals(line7, filteredText.get(4));
		Assertions.assertTrue(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}

	@Test
	public void handleBlockComment_LeadingAndTrailingBrokenComments_AllCommentsSurroundedByMultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "abc";
		var line2 = "\"\"\"";
		var line3 = "*/";
		var line4 = "def";
		var line5 = "/*";
		var line6 = "\"\"\"";
		var line7 = "hgf";

		var text = concatLines(line1, line2, line3, line4, line5, line6, line7);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(7, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
		Assertions.assertEquals(line3, filteredText.get(2));
		Assertions.assertEquals(line4, filteredText.get(3));
		Assertions.assertEquals(line5, filteredText.get(4));
		Assertions.assertEquals(line6, filteredText.get(5));
		Assertions.assertEquals(line7, filteredText.get(6));
		Assertions.assertFalse(cr.checkForLeadingBrokenBlockCommentary(text));
		Assertions.assertFalse(cr.checkForTrailingBrokenBlockCommentary(text));
	}
}
