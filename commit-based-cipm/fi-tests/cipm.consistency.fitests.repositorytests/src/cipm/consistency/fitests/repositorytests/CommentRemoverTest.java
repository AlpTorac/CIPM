package cipm.consistency.fitests.repositorytests;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

public class CommentRemoverTest {
//	private static String[][] generateParamFor(String[] lines, String[] precedingCode, String[] followingCode, String[] expectedFilteredText) {
//		var result = new String[lines.length][2];
//		
//		for (int i = 0; i < lines.length; i++) {
//			result[i][0] = precedingCode[i] + lines[i] + followingCode[i];
//			result[i][1] = expectedFilteredText[i];
//		}
//
//		return result;
//	}

	private static String concatLines(String... lines) {
		var result = "";

		for (int i = 0; i < lines.length - 1; i++)
			result += lines[i] + System.lineSeparator();

		result += lines[lines.length - 1];

		return result;
	}

	@Test
	public void removeSingleLineComment_PrecedingCode() {
		var cr = new CommentRemover();

		var code = "def ";
		var line1 = code + "// abc";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_NoContext() {
		var cr = new CommentRemover();

		var line1 = "// abc";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_PrecedingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "// abc ";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_FollowingContext() {
		var cr = new CommentRemover();

		var line1 = "// abc ";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_SurroundingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "// abc ";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = cr.removeCommentary(text);

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

//	@Test
//	public void removeSingleLineComment_MultipleLines_NoContext() {
//		var cr = new CommentRemover();
//
//		var line1 = "// abc ";
//		var line2 = "// def";
//
//		var text = concatLines(line1, line2);
//
//		var filteredText = cr.removeCommentary(text);
//		Assertions.assertEquals(0, filteredText.size());
//	}
//
//	@Test
//	public void removeSingleLineComment_MultipleLines_SandwitchedContext() {
//		var cr = new CommentRemover();
//
//		var line1 = "// abc ";
//		var line2 = "123 ";
//		var line3 = "// def";
//
//		var text = concatLines(line1, line2, line3);
//
//		var filteredText = cr.removeCommentary(text);
//		Assertions.assertEquals(1, filteredText.size());
//		Assertions.assertEquals(line2, filteredText.get(0));
//	}

	@Test
	public void removeBlockComment_SingleLine_PrecedingCode() {
		var cr = new CommentRemover();

		var code = "def ";
		var line1 = code + "/* abc */";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingCode() {
		var cr = new CommentRemover();

		var code = "def ";
		var line1 = "/* abc */" + code;

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_SurroundingCode() {
		var cr = new CommentRemover();

		var code1 = "def ";
		var code2 = "hgf ";
		var line1 = code1 + "/* abc */" + code2;

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_NoContext() {
		var cr = new CommentRemover();

		var line1 = "/* abc */";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_SingleLine_PrecedingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "/* abc */";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingContext() {
		var cr = new CommentRemover();

		var line1 = "/* abc */";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_SurroundingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "/* abc */";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = cr.removeCommentary(text);

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	// @Test
	// public void removeSingleLineComment_MultipleLines_NoContext() {
	// var cr = new CommentRemover();
	//
	// var line1 = "// abc ";
	// var line2 = "// def";
	//
	// var text = concatLines(line1, line2);
	//
	// var filteredText = cr.removeCommentary(text);
	// Assertions.assertEquals(0, filteredText.size());
	// }
	//
	// @Test
	// public void removeSingleLineComment_MultipleLines_SandwitchedContext() {
	// var cr = new CommentRemover();
	//
	// var line1 = "// abc ";
	// var line2 = "123 ";
	// var line3 = "// def";
	//
	// var text = concatLines(line1, line2, line3);
	//
	// var filteredText = cr.removeCommentary(text);
	// Assertions.assertEquals(1, filteredText.size());
	// Assertions.assertEquals(line2, filteredText.get(0));
	// }

	@Test
	public void removeJavaDoc_SingleLine_PrecedingCode() {
		var cr = new CommentRemover();

		var code = "def ";
		var line1 = code + "/** abc */";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_FollowingCode() {
		var cr = new CommentRemover();

		var code = "def ";
		var line1 = "/** abc */" + code;

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_SurroundingCode() {
		var cr = new CommentRemover();

		var code1 = "def ";
		var code2 = "hgf ";
		var line1 = code1 + "/** abc */" + code2;

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_NoContext() {
		var cr = new CommentRemover();

		var line1 = "/** abc */";

		var text = concatLines(line1);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeJavaDoc_SingleLine_PrecedingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "/** abc */";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_FollowingContext() {
		var cr = new CommentRemover();

		var line1 = "/** abc */";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = cr.removeCommentary(text);
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_SurroundingContext() {
		var cr = new CommentRemover();

		var line1 = "def ";
		var line2 = "/** abc */";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = cr.removeCommentary(text);

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}
}
