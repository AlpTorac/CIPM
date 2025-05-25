package cipm.consistency.fitests.repositorytests.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;

import cipm.consistency.fitests.repositorytests.util.commentremoval.QuickCommentRemover;
import cipm.consistency.fitests.repositorytests.util.difffilter.DiffFilter;

public class RepoTestSimilarityValueEstimator {
	public boolean getExpectedSimilarityValueFor(Git git, AbstractTreeIterator oldTreeIter,
			AbstractTreeIterator newTreeIter) {
		var osOutput = "";
		var filter = new DiffFilter();
		var cr = new QuickCommentRemover();

		try (var os = new ByteArrayOutputStream(); var df = new DiffFormatter(os)) {
			df.setRepository(git.getRepository());
			df.setContext(10);
//			df.setPathFilter(PathSuffixFilter.create(".java"));

			// Include to get patches
			df.format(oldTreeIter, newTreeIter);
			osOutput = os.toString();

//			var entries = df.scan(oldTreeIter, newTreeIter);
//
//			for (var e : entries) {
//				df.format(e);
//				osOutput += os.toString();
//			}

			df.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		var lines = filter.filterIrrelevantLines(osOutput);
		var text = lines.stream().reduce("", (l1, l2) -> String.format("%s%s%s", l1, System.lineSeparator(), l2));
		text = cr.removeComments(text);
		text = filter.removeBlankLines(filter.removeContextLines(filter.splitLines(text, System.lineSeparator())))
				.stream().reduce("", (l1, l2) -> String.format("%s%s%s", l1, System.lineSeparator(), l2));

		System.out.println(text);
		return text.isBlank();
	}
}
