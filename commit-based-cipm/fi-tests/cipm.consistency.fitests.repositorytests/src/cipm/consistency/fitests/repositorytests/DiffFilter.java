package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class DiffFilter {
	private static final Pattern diffCommandPattern = Pattern.compile("^\\s*diff --git .*");
	private static final Pattern diffLocationPattern = Pattern.compile("^\\s*@@ .* @@\\s*");
	private static final Pattern diffIndexPattern = Pattern.compile("^\\s*index .*");
	private static final Pattern diffFileAddPattern = Pattern.compile("^\\s*\\+\\+\\+ .*\\.\\w*");
	private static final Pattern diffFileRemovePattern = Pattern.compile("^\\s*--- .*\\.\\w*");

	private static final Pattern diffNoNewLinePattern = Pattern.compile("\\ No newline at end of file");

	private static final String unixNewLine = "\\n";

	private List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		// Do not use System.lineSeparator since GIT uses UNIX terminal
		// UNIX terminal uses "\n" for new line
		var diffLines = diff.split(unixNewLine);

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}

	public List<String> filterIrrelevantLines(List<String> lines) {
		lines.removeIf((l) -> !isContentLine(l));
		return lines;
	}

	public List<String> filterIrrelevantLines(String diff) {
		var lines = this.splitLines(diff);
		return filterIrrelevantLines(lines);
	}

	public List<String> removeBlankLines(List<String> lines) {
		lines.removeIf((l) -> l.isBlank());
		return lines;
	}

	public boolean isContentLine(String line) {
		if (diffCommandPattern.matcher(line).find()) {
			return false;
		} else if (diffFileAddPattern.matcher(line).find()) {
			return false;
		} else if (diffFileRemovePattern.matcher(line).find()) {
			return false;
		} else if (diffIndexPattern.matcher(line).find()) {
			return false;
		} else if (diffLocationPattern.matcher(line).find()) {
			return false;
		} else if (diffNoNewLinePattern.matcher(line).find()) {
			return false;
		} else {
			return true;
		}
	}
}
