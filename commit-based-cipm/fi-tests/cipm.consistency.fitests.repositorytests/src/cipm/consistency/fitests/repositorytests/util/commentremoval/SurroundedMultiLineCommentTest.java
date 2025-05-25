package cipm.consistency.fitests.repositorytests.util.commentremoval;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.util.AbstractJaMoPPParserRepoUtilTest;

/**
 * TODO Write proper commentary
 * 
 * Tests within this class are supposed to simulate cases, where the text is
 * only a snippet of the code and string declarations are broken. Since there
 * are different ways to interpret the given code without having access to all
 * of it, adaptations to test results may be necessary in the future.
 * 
 * @author Alp Torac Genc
 */
public class SurroundedMultiLineCommentTest extends AbstractJaMoPPParserRepoUtilTest {
	private static final String multiLineStringToken = "\"\"\"";
	private ICommentRemover cr = new QuickCommentRemover();

	@Test
	public void handleStringLiteral_SingleLineComment_StartAndStringOnSameLine() {
		var line1 = multiLineStringToken + "//abc";
		var line2 = multiLineStringToken;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(multiLineStringToken, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_EndAndStringOnSameLine() {
		var line1 = multiLineStringToken;
		var line2 = "//abc\"\"\"";

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void handleStringLiteral_SingleLineComment_SurroundingStartAndEnd() {
		var line1 = multiLineStringToken;
		var line2 = "//abc";
		var line3 = multiLineStringToken;

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
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
		var line1 = multiLineStringToken + "/*abc*/";
		var line2 = multiLineStringToken;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(multiLineStringToken, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_EndAndStringOnSameLine() {
		var line1 = multiLineStringToken;
		var line2 = "/*abc*/" + multiLineStringToken;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(multiLineStringToken, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_MultiLineComment_SurroundingStartAndEnd() {
		var line1 = multiLineStringToken;
		var line2 = "/*abc*/";
		var line3 = multiLineStringToken;

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
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
		var line1 = multiLineStringToken + "/**abc*/";
		var line2 = multiLineStringToken;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(multiLineStringToken, filteredText.get(0));
		Assertions.assertEquals(line2, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_EndAndStringOnSameLine() {
		var line1 = multiLineStringToken;
		var line2 = "/**abc*/" + multiLineStringToken;

		var text = concatLines(line1, line2);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(multiLineStringToken, filteredText.get(1));
	}

	@Test
	public void handleStringLiteral_JavaDoc_SurroundingStartAndEnd() {
		var line1 = multiLineStringToken;
		var line2 = "/**abc*/";
		var line3 = multiLineStringToken;

		var text = concatLines(line1, line2, line3);

		var filteredText = this.removeBlankLines(this.splitLines(cr.removeComments(text)));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}
}
