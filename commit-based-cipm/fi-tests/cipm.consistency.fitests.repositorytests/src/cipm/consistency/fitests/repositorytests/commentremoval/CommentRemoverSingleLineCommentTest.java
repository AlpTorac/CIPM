package cipm.consistency.fitests.repositorytests.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentRemoverSingleLineCommentTest extends CommentRemoverTest {
	@Test
	public void removeSingleLineComment_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "// abc";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "// abc ";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc ";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "// abc";
		var line2 = "// hgf";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc";
		var line2 = "// def";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_MultipleComments_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "// hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc ";
		var line2 = "// hgf ";
		var line3 = "def ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "// jkl ";
		var line4 = "hgf ";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line4, filteredText.get(1));
	}

	@Test
	public void removeSingleLineComment_RepeatingSlashes() {
		var cr = new CommentRemoverLexer();

		var line1 = "// // abc";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_SurroundingSlashes() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc //";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_SingleLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "// \"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral() {
		var cr = new CommentRemoverLexer();

		var line1 = "// \"\"\"abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral_Split() {
		var cr = new CommentRemoverLexer();

		var line1 = "// \"\"\"abc";
		var line2 = "// \"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeEmptyLines(this.splitLines(cr.removeCommentary(text)));
		Assertions.assertEquals(0, filteredText.size());
	}
}
