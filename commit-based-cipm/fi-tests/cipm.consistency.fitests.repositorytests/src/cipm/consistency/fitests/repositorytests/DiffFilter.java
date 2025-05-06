package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.List;

public class DiffFilter {
	private static final String diffCommandPattern = "diff --git .*";
	private static final String diffLocationPattern = "@@ .* @@";
	private static final String diffIndexPattern = "index .*";
	private static final String diffFileAddPattern = "\\+\\+\\+ .*\\.\\w*";
	private static final String diffFileRemovePattern = "--- .*\\.\\w*";
	
	private List<String> splitLines(String diff) {
		var lines = new ArrayList<String>();

		var diffLines = diff.split(System.lineSeparator());

		for (var l : diffLines) {
			lines.add(l);
		}

		return lines;
	}
	
	public List<String> filterIrrelevantLines(List<String> lines) {
		var result = lines.subList(0, lines.size());
		result.removeIf((l) -> !isContentLine(l));
		return result;
	}
	
	public List<String> filterIrrelevantLines(String diff) {
		var lines = this.splitLines(diff);
		lines = filterIrrelevantLines(lines);
		return lines;
	}
	
	public boolean isContentLine(String line) {
		if (line.matches(diffCommandPattern)) {
			return false;
		} else if (line.matches(diffFileAddPattern)) {
			return false;
		} else if (line.matches(diffFileRemovePattern)) {
			return false;
		} else if (line.matches(diffLocationPattern)) {
			return false;
		} else if (line.matches(diffIndexPattern)) {
			return false;
		} else {
			return true;
		}
	}
}
