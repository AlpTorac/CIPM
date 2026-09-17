package cipm.consistency.fitests.repositorytests.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.diff.Edit;
import org.eclipse.jgit.diff.EditList;
import org.eclipse.jgit.diff.RawText;
import org.eclipse.jgit.patch.FileHeader;
import org.eclipse.jgit.treewalk.AbstractTreeIterator;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.treewalk.filter.PathSuffixFilter;
import org.eclipse.jgit.util.StringUtils;

import cipm.consistency.fitests.repositorytests.util.commentremoval.QuickCommentRemover;
import cipm.consistency.fitests.repositorytests.util.difffilter.DiffFilter;

/**
 * A class that computes expected similarity checking results based on the given
 * GIT-Diffs. Provides numerous variants of its computation method to allow
 * re-using various GIT elements.<br>
 * <br>
 * Uses {@link QuickCommentRemover}, which removes commentaries in an
 * approximative fashion. Therefore, the computed results may not always be
 * fully accurate.
 * 
 * @author Alp Torac Genc
 */
public class RepoTestSimilarityValueEstimator {
	private static final String treeIDSuffix = "^{tree}";
	private static final int defaultContextLineCount = 3;
	private int contextLineCount = defaultContextLineCount;

	/**
	 * @param git       The object enclosing the GIT-repository that contains the
	 *                  given commits
	 * @param commitID1 A commit from git
	 * @param commitID2 Another commit from git
	 * @return Whether model resources parsed from the given commits are similar
	 *         according to this instance
	 */
	public boolean getExpectedSimilarityValueFor(Git git, String commitID1, String commitID2) {
		try (var reader = git.getRepository().newObjectReader();
				var os = new ByteArrayOutputStream();
				var df = new DiffFormatter(os)) {

			df.setRepository(git.getRepository());
			df.setContext(this.getContextLineCount());
			df.setPathFilter(PathSuffixFilter.create(".java"));

			var oldTreeIter = new CanonicalTreeParser();
			var oldTree = git.getRepository().resolve(commitID1 + treeIDSuffix);
			oldTreeIter.reset(reader, oldTree);

			var newTreeIter = new CanonicalTreeParser();
			var newTree = git.getRepository().resolve(commitID2 + treeIDSuffix);
			newTreeIter.reset(reader, newTree);

			var diffEntries = df.scan(oldTreeIter, newTreeIter);

			List<String> removedLines = new ArrayList<>();
			List<String> addedLines = new ArrayList<>();

			for (var entry : diffEntries) {
				FileHeader header = df.toFileHeader(entry);
				EditList edits = header.toEditList();

				var abbrOldObjId = entry.getOldId();
				var abbrNewObjId = entry.getNewId();

				var oldObjId = abbrOldObjId != null ? abbrOldObjId.toObjectId() : null;
				var newObjId = abbrNewObjId != null ? abbrNewObjId.toObjectId() : null;

				var oldObj = reader.has(oldObjId) ? reader.open(oldObjId) : null;
				var newObj = reader.has(newObjId) ? reader.open(newObjId) : null;

				RawText oldText = oldObj != null ? new RawText(oldObj.getBytes()) : null;
				RawText newText = newObj != null ? new RawText(newObj.getBytes()) : null;

				for (Edit edit : edits) {
					// Removed lines (old side) -> '-' prefix
					if (oldText != null) {
						for (int i = edit.getBeginA(); i < edit.getEndA(); i++) {
							String line = oldText.getString(i).trim();
							if (!line.isBlank())
								removedLines.add(line);
						}
					}
					// Added lines (new side) -> '+' prefix
					if (newText != null) {
						for (int i = edit.getBeginB(); i < edit.getEndB(); i++) {
							String line = newText.getString(i).trim();
							if (!line.isBlank())
								addedLines.add(line);
						}
					}
				}
			}

			var filter = new DiffFilter();
			var cr = new QuickCommentRemover();

			var added = StringUtils.join(filter.splitLines(cr.removeComments(filter.concatLines(addedLines))), "");
			var removed = StringUtils.join(filter.splitLines(cr.removeComments(filter.concatLines(removedLines))), "");

			return added.equals(removed);
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalStateException("IOException occured while computing expected similarity result", e);
		}
	}

	/**
	 * @return The number of context lines that will be considered while diffing, if
	 *         no {@link DiffFormatter} is explicitly provided. Defaults to
	 *         {@value #defaultContextLineCount}, unless re-set via
	 *         {@link #setContextLineCount(int)}.
	 */
	public int getContextLineCount() {
		return this.contextLineCount;
	}

	/**
	 * {@link #getContextLineCount()}
	 */
	public void setContextLineCount(int contextLineCount) {
		this.contextLineCount = contextLineCount;
	}
}
