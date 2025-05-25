package cipm.consistency.fitests.repositorytests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.repositorytests.util.RepoTestSimilarityValueEstimator;
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

		// From final commit towards initial commit
		for (int i = 1; i < commitCount; i++) {
			int j = i - 1;
			var reader = git.getRepository().newObjectReader();

			var oldTreeIter = new CanonicalTreeParser();
			var oldTree = git.getRepository().resolve("HEAD~" + (j) + "^{tree}");
			var oldCommit = git.getRepository().resolve("HEAD~" + (j));
			oldTreeIter.reset(reader, oldTree);

			var newTreeIter = new CanonicalTreeParser();
			var newTree = git.getRepository().resolve("HEAD~" + i + "^{tree}");
			var newCommit = git.getRepository().resolve("HEAD~" + i);
			newTreeIter.reset(reader, newTree);

			System.out.println(String.format("old: %s (%s), new: %s (%s)", j, oldCommit, i, newCommit));
			System.out.println(String.format("Expected similarity value: %s", new RepoTestSimilarityValueEstimator()
					.getExpectedSimilarityValueFor(git, oldTreeIter, newTreeIter)));
		}

		// From initial commit towards final commit
		for (int i = 1; i < commitCount; i++) {
			int idx = commitCount - i;
			int j = commitCount - i - 1;

			var reader = git.getRepository().newObjectReader();

			var oldTreeIter = new CanonicalTreeParser();
			var oldTree = git.getRepository().resolve("HEAD~" + (j) + "^{tree}");
			var oldCommit = git.getRepository().resolve("HEAD~" + (j));
			oldTreeIter.reset(reader, oldTree);

			var newTreeIter = new CanonicalTreeParser();
			var newTree = git.getRepository().resolve("HEAD~" + idx + "^{tree}");
			var newCommit = git.getRepository().resolve("HEAD~" + idx);
			newTreeIter.reset(reader, newTree);

			System.out.println(String.format("old: %s (%s), new: %s (%s)", j, oldCommit, idx, newCommit));
			System.out.println(String.format("Expected similarity value: %s", new RepoTestSimilarityValueEstimator()
					.getExpectedSimilarityValueFor(git, oldTreeIter, newTreeIter)));
		}
	}
}
