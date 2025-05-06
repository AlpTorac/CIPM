package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommentRemoverTest {
//	private static Stream<Arguments> genParams() {
//		var args = new ArrayList<Arguments>();
//		
//		args.add(Arguments.of("Single line, preceding code", 
//				generateParams(
//						new String[][] {
//			new String[] {"def ", "// abc"},
//			new String[] {"// abc"}
//				}, new String[] {
//						"def ",
//						""
//				})));
//		
//		return args.stream();
//	}
//	
//	private static List<String[]> generateParams(String[][] lines, String[] expectedFilteredLines) {
//		var list = new ArrayList<String[]>();
//		
//		for (int i = 0; i < lines[0].length; i++) {
//			list.add(new String[] {concatLines(lines[i]), expectedFilteredLines[i]});
//		}
//		
//		return list;
//	}
//
//	@ParameterizedTest(name="{0}")
//	@MethodSource("genParams")
//	public void removeComment(String display, List<String[]> params) {
//		var cr = new CommentRemoverLexer();
//
//		var lines = params.stream().map((arr) -> arr[0]).toArray(String[]::new);
//		var expLines = params.stream().map((arr) -> arr[1]).toArray(String[]::new);
//		
//		var text = concatLines(lines);
//
//		var filteredText = this.splitLines(cr.removeCommentary(text));
//
//		int filteredTextLength = 0;
//		
//		for (int i = 0; i < filteredText.size(); i++) {
//			var expLine = expLines[i];
//			Assertions.assertEquals(expLine, filteredText.get(i));
//			
//			if (expLine != null && !expLine.isEmpty() && !expLine.isBlank())
//				filteredTextLength++;
//		}
//		
//		Assertions.assertEquals(filteredTextLength, filteredText.size());
//	}
	
	private List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		var diffLines = diff.split(System.lineSeparator());

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}
	
	private static String concatLines(String... lines) {
		var result = "";

		for (int i = 0; i < lines.length - 1; i++)
			result += lines[i] + System.lineSeparator();

		result += lines[lines.length - 1];

		return result;
	}

	@Test
	public void removeSingleLineComment_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "// abc";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeSingleLineComment_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "// abc ";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeSingleLineComment_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "// abc ";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

//	@Test
//	public void removeSingleLineComment_MultipleLines_NoContext() {
//		var cr = new CommentRemoverLexer();
//
//		var line1 = "// abc ";
//		var line2 = "// def";
//
//		var text = concatLines(line1, line2);
//
//		var filteredText = this.splitLines(cr.removeCommentary(text));
//		Assertions.assertEquals(0, filteredText.size());
//	}
//
//	@Test
//	public void removeSingleLineComment_MultipleLines_SandwitchedContext() {
//		var cr = new CommentRemoverLexer();
//
//		var line1 = "// abc ";
//		var line2 = "123 ";
//		var line3 = "// def";
//
//		var text = concatLines(line1, line2, line3);
//
//		var filteredText = this.splitLines(cr.removeCommentary(text));
//		Assertions.assertEquals(1, filteredText.size());
//		Assertions.assertEquals(line2, filteredText.get(0));
//	}

	@Test
	public void removeBlockComment_SingleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "/* abc */";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = "/* abc */" + code;

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_SingleLine_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/* abc */";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeBlockComment_SingleLine_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* abc */";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	// @Test
	// public void removeSingleLineComment_MultipleLines_NoContext() {
	// var cr = new CommentRemoverLexer();
	//
	// var line1 = "// abc ";
	// var line2 = "// def";
	//
	// var text = concatLines(line1, line2);
	//
	// var filteredText = this.splitLines(cr.removeCommentary(text));
	// Assertions.assertEquals(0, filteredText.size());
	// }
	//
	// @Test
	// public void removeSingleLineComment_MultipleLines_SandwitchedContext() {
	// var cr = new CommentRemoverLexer();
	//
	// var line1 = "// abc ";
	// var line2 = "123 ";
	// var line3 = "// def";
	//
	// var text = concatLines(line1, line2, line3);
	//
	// var filteredText = this.splitLines(cr.removeCommentary(text));
	// Assertions.assertEquals(1, filteredText.size());
	// Assertions.assertEquals(line2, filteredText.get(0));
	// }

	// @Test
	// public void removeSingleLineComment_MultipleLines_NoContext() {
	// var cr = new CommentRemoverLexer();
	//
	// var line1 = "// abc ";
	// var line2 = "// def";
	//
	// var text = concatLines(line1, line2);
	//
	// var filteredText = this.splitLines(cr.removeCommentary(text));
	// Assertions.assertEquals(0, filteredText.size());
	// }
	//
	// @Test
	// public void removeSingleLineComment_MultipleLines_SandwitchedContext() {
	// var cr = new CommentRemoverLexer();
	//
	// var line1 = "// abc ";
	// var line2 = "123 ";
	// var line3 = "// def";
	//
	// var text = concatLines(line1, line2, line3);
	//
	// var filteredText = this.splitLines(cr.removeCommentary(text));
	// Assertions.assertEquals(1, filteredText.size());
	// Assertions.assertEquals(line2, filteredText.get(0));
	// }

	@Test
	public void removeBlockComment_MultipleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";
		var line1 = code + "/*";
		var line2 = "abc */";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(code1, filteredText.get(0));
		Assertions.assertEquals(code2, filteredText.get(1));
	}

	@Test
	public void removeBlockComment_MultipleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/* ";
		var line2 = "abc";
		var line3 = "*/";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeBlockComment_MultipleLine_NoContext_WithStarInBody() {
		var cr = new CommentRemoverLexer();

		var line1 = "/*";
		var line2 = " * abc";
		var line3 = " */";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));
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

		var filteredText = this.splitLines(cr.removeCommentary(text));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line5, filteredText.get(1));
	}

	@Test
	public void removeJavaDoc_SingleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";

		var line1 = code + "/** abc */";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_FollowingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";

		var line1 = "/** abc */" + code;

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_SurroundingCode() {
		var cr = new CommentRemoverLexer();

		var code1 = "def ";
		var code2 = "hgf ";

		var line1 = code1 + "/** abc */" + code2;

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code1 + code2, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/** abc */";

		var text = concatLines(line1);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeJavaDoc_SingleLine_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/** abc */";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/** abc */";
		var line2 = "def ";

		var text = concatLines(line1, line2);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line2, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_SingleLine_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/** abc */";
		var line3 = "hgf ";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line3, filteredText.get(1));
	}

	@Test
	public void removeJavaDoc_MultipleLine_PrecedingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";

		var line1 = code + "/**";
		var line2 = "abc";
		var line3 = "*/";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_MultipleLine_FollowingCode() {
		var cr = new CommentRemoverLexer();

		var code = "def ";

		var line1 = "/**";
		var line2 = "abc";
		var line3 = "*/" + code;

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(code, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_MultipleLine_SurroundingCode() {
		var cr = new CommentRemoverLexer();

		var code1 = "def ";
		var code2 = "hgf ";

		var line1 = code1 + "/**";
		var line2 = "abc";
		var line3 = "*/" + code2;

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(code1, filteredText.get(0));
		Assertions.assertEquals(code2, filteredText.get(1));
	}

	@Test
	public void removeJavaDoc_MultipleLine_NoContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/**";
		var line2 = "abc";
		var line3 = "*/";

		var text = concatLines(line1, line2, line3);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(0, filteredText.size());
	}

	@Test
	public void removeJavaDoc_MultipleLine_PrecedingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/**";
		var line3 = "abc";
		var line4 = "*/";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_MultipleLine_FollowingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "/**";
		var line2 = "abc";
		var line3 = "*/";
		var line4 = "def ";

		var text = concatLines(line1, line2, line3, line4);

		var filteredText = this.splitLines(cr.removeCommentary(text));
		Assertions.assertEquals(1, filteredText.size());
		Assertions.assertEquals(line4, filteredText.get(0));
	}

	@Test
	public void removeJavaDoc_MultipleLine_SurroundingContext() {
		var cr = new CommentRemoverLexer();

		var line1 = "def ";
		var line2 = "/**";
		var line3 = "abc";
		var line4 = "*/";
		var line5 = "hgf ";

		var text = concatLines(line1, line2, line3, line4, line5);

		var filteredText = this.splitLines(cr.removeCommentary(text));

		Assertions.assertEquals(2, filteredText.size());
		Assertions.assertEquals(line1, filteredText.get(0));
		Assertions.assertEquals(line5, filteredText.get(1));
	}
	
	// TODO Add tests for commentary tokens in string literals
	// TODO Add tests for multiple commentaries in a single line
	
}
