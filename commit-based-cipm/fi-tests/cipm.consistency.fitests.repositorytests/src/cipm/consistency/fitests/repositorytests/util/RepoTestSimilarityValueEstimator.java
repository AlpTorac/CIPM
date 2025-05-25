package cipm.consistency.fitests.repositorytests.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;

import cipm.consistency.fitests.repositorytests.util.commentremoval.QuickCommentRemover;
import cipm.consistency.fitests.repositorytests.util.difffilter.DiffFilter;

public class RepoTestSimilarityValueEstimator {
	public boolean getExpectedSimilarityValueFor(Git git, AbstractTreeIterator oldTreeIter,
			AbstractTreeIterator newTreeIter) {
		try (var os = new ByteArrayOutputStream(); var df = new DiffFormatter(os)) {
			df.setRepository(git.getRepository());
			df.setContext(3);
//			df.setPathFilter(PathSuffixFilter.create(".java"));

			var entries = df.scan(oldTreeIter, newTreeIter);

			for (var e : entries) {
				df.format(e);
				// Adapt all UNIX new lines to the current system
				var code = this.getEffectiveLines(os.toString().replaceAll("\\n", System.lineSeparator()));
				System.out.println(code);
				var expectedSimVal = this.computeExpectedSimilarityValue(code);
				if (!expectedSimVal)
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("IOException occured while computing expected similarity value", e);
		}

		return true;
	}

	public List<String> getEffectiveLines(String text) {
		var filter = new DiffFilter();
		var cr = new QuickCommentRemover();

		var result = cr.removeComments(text);
		var lines = filter.splitLines(result);
		lines = filter.removeContextLines(lines);
		lines = filter.removeNonPatchScript(lines);
		lines = filter.removeBlankLines(lines);

		return lines;
	}

	public boolean computeExpectedSimilarityValue(List<String> lines) {
		var added = new ArrayList<String>();
		var removed = new ArrayList<String>();

		lines.stream().forEach((l) -> {
			if (l.startsWith("+"))
				added.add(l.substring(1).replaceAll("\\s", ""));
			if (l.startsWith("-"))
				removed.add(l.substring(1).replaceAll("\\s", ""));
		});

		var allAdded = added.stream().reduce("", (t1, t2) -> t1 + t2);
		var allRemoved = removed.stream().reduce("", (t1, t2) -> t1 + t2);

		return allAdded.equals(allRemoved);
	}
}
