package cipm.consistency.fitests.repositorytests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.AnyObjectId;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.AndTreeFilter;
import org.eclipse.jgit.treewalk.filter.NotIgnoredFilter;
import org.eclipse.jgit.treewalk.filter.NotTreeFilter;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;
import org.eclipse.jgit.treewalk.filter.TreeFilter;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.jamopp.parser.FileUtil;

public class TeammatesJGITTest {
	@Test
	public void test() throws Exception {
		/*
		 * Diff: Minimal change (change type + what changed) (with diffFormatter.scan)
		 * 
		 * Patch: Changes with full details (with diffFormatter.format)
		 */

		var repoFile = new File("C:\\Users\\sdq-l\\OneDrive\\Desktop\\testRepo");
		var cloneDir = new File("C:\\Users\\sdq-l\\OneDrive\\Desktop\\testRepoClone");

		if (cloneDir.exists()) {
			new FileUtil().cleanModels(cloneDir);
		}

		var git = Git.cloneRepository().setURI(repoFile.toURI().toString()).setDirectory(cloneDir).call();

		List<RevCommit> listOfCommits = new ArrayList<>();
		try {
			git.log().add(git.getRepository().resolve("master")).call().forEach(listOfCommits::add);
		} catch (RevisionSyntaxException | GitAPIException | IOException e) {
		}

		int commitCount = listOfCommits.size();
		
		for (int i = 0; i < commitCount; i++) {
			for (int j = 0; j < commitCount; j++) {
				var reader = git.getRepository().newObjectReader();

				var oldTreeIter = new CanonicalTreeParser();
				var oldTree = git.getRepository().resolve("HEAD~" + (i) + "^{tree}");
				var oldCommit = git.getRepository().resolve("HEAD~" + (i));
				oldTreeIter.reset(reader, oldTree);

				var newTreeIter = new CanonicalTreeParser();
				var newTree = git.getRepository().resolve("HEAD~" + j + "^{tree}");
				var newCommit = git.getRepository().resolve("HEAD~" + j);
				newTreeIter.reset(reader, newTree);

				System.out.println(String.format("old: %s (%s), new: %s (%s)", i, oldCommit, j, newCommit));
				this.outputRelevantDiffs(git, oldTreeIter, newTreeIter);
			}
		}

	}

	private void outputRelevantDiffs(Git git, AbstractTreeIterator oldTreeIter, AbstractTreeIterator newTreeIter) {
		var osOutput = "";

		try (
				var os = new ByteArrayOutputStream();
				DiffFormatter df = new DiffFormatter(os)
			) {
			df.setRepository(git.getRepository());
			df.setContext(1000);
//			df.setPathFilter(PathSuffixFilter.create(".java"));
			
			// Include to get patches
			df.format(oldTreeIter, newTreeIter);
//			var entries = df.scan(oldTreeIter, newTreeIter);
			
			osOutput = os.toString();
			df.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		var osOutputLines = new ArrayList<String>();
		var commitAnalyser = new CommentRemover();
		var filteredLines = commitAnalyser.removeCommentary(osOutput);
		filteredLines = new DiffFilter().filterIrrelevantLines(filteredLines);
		
		for (var l : filteredLines) {
			osOutputLines.add(l);
			System.out.println(l);
		}

		var filteredOutput = osOutputLines.stream().reduce("", (l1, l2) -> l1 + l2);
	}
}
