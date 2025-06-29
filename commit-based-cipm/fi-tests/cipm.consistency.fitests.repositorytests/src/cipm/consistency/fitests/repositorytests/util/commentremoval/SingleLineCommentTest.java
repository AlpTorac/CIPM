package cipm.consistency.fitests.repositorytests.util.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.util.AbstractJaMoPPParserRepoUtilTest;

/**
 * TODO Write proper commentary
 */
public class SingleLineCommentTest extends AbstractJaMoPPParserRepoUtilTest {
	private ICommentRemover cr = new QuickCommentRemover();

	@Test
	public void removeSingleLineComment_PrecedingCode() {
		var code = "def ";
		var line1 = code + "// abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_NoContext() {
		var line1 = "// abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_PrecedingContext() {
		var line1 = "def ";
		var line2 = "// abc ";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_FollowingContext() {
		var line1 = "// abc ";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_SurroundingContext() {
		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_PrecedingCode() {
		var code = "def ";
		var line1 = code + "// abc";
		var line2 = "// hgf";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_NoContext() {
		var line1 = "// abc";
		var line2 = "// def";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_MultipleComments_PrecedingContext() {
		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "// hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_FollowingContext() {
		var line1 = "// abc ";
		var line2 = "// hgf ";
		var line3 = "def ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line3, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_MultipleComments_SurroundingContext() {
		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "// jkl ";
		var line4 = "hgf ";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line4, filteredText.get(1));
	}

	@Test
	public void removeSingleLineComment_RepeatingSlashes() {
		var line1 = "// // abc";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_SurroundingSlashes() {
		var line1 = "// abc //";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_SingleLineStringLiteral() {
		var line1 = "// \"abc\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral() {
		var line1 = "// \"\"\"abc\"\"\"";

		var text = concatLines(line1);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void handleStringLiteralInComment_MultiLineStringLiteral_Split() {
		var line1 = "// \"\"\"abc";
		var line2 = "// \"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(0, filteredText.size());
	}
}
