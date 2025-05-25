package cipm.consistency.fitests.repositorytests.util;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractJaMoPPParserRepoUtilTest {
	protected List<String> removeBlankLines(List<String> lines) {
		lines.removeIf((l) -> l.isBlank());
		return lines;
	}

	protected List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		var diffLines = diff.split(System.lineSeparator());

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}

	protected String concatLines(String... lines) {
		var result = "";

		for (int i = 0; i < lines.length - 1; i++)
			result += lines[i] + System.lineSeparator();

		result += lines[lines.length - 1];

		return result;
	}
}
