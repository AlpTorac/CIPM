package cipm.consistency.fitests.repositorytests;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
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

		var reader = git.getRepository().newObjectReader();

		var oldTreeIter = new CanonicalTreeParser();
		var oldTree = git.getRepository().resolve("HEAD^{tree}"); // equals newCommit.getTree()
		oldTreeIter.reset(reader, oldTree);

		var newTreeIter = new CanonicalTreeParser();
		var newTree = git.getRepository().resolve("HEAD~1^{tree}"); // equals oldCommit.getTree()
		newTreeIter.reset(reader, newTree);

		var os = new ByteArrayOutputStream();

		DiffFormatter df = new DiffFormatter(os); // use NullOutputStream.INSTANCE if you don't need the diff output
		df.setRepository(git.getRepository());
		df.format(oldTreeIter, newTreeIter); // Include to get patches

		System.out.println(os.toString());
		df.close();
		os.close();
	}
}
