package cipm.consistency.fitests.repositorytests.util;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class that stores expected similarity checking results of the commits with
 * the given IDs.
 * 
 * Assumptions on similarity checking:
 * <ul>
 * <li>Reflexivity: Same commits are similar with respect to similarity checking
 * <li>Symmetry: Similarity is symmetric (i.e. swapping commitID1 and commitID2
 * does not change the similarity result)
 * <li>TODO Account for the transitivity of similarity results. Transitivity:
 * Assuming C1, C2 and C3 are different commits; if C1 and C2 are similar, C2
 * and C3 are similar; then C1 and C3 should also be similar. Transitivity is
 * currently NOT supported.
 * </ul>
 * 
 * The expected similarity result for commits C_X and C_Y can be determined by
 * using the assumptions above, without explicitly added expected similarity
 * results. In such cases, there may not be any expected similarity result for
 * C_X and C_Y in this cache.
 * 
 * @author Alp Torac Genc
 */
public class RepoTestResultCache {
	private final Collection<SimilarityResultEntry> similarityResults = new ArrayList<SimilarityResultEntry>();

	/**
	 * Constructs an instance with no similarity results.
	 */
	public RepoTestResultCache() {
	}

	/**
	 * Constructs an instance and copies the contents of the given cache into this
	 * cache.
	 */
	public RepoTestResultCache(RepoTestResultCache cache) {
		this.copyResultsOf(cache, true);
	}

	/**
	 * Copies all expected similarity results into this cache.
	 * 
	 * @param overrideResultIfPresent Whether the copied expected similarity results
	 *                                should override any potentially existing ones
	 */
	public void copyResultsOf(RepoTestResultCache cache, boolean overrideResultIfPresent) {
		this.similarityResults.addAll(cache.similarityResults);
	}

	/**
	 * Adds the expected similarity result denoted by the parameters:
	 * {@code isSimilar(commitID1, commitID2) = expectedResult}
	 * 
	 * @param overrideResultIfPresent Whether the potentially existing result should
	 *                                be overridden. Note that the existing result
	 *                                could be stored in an entry, where the given
	 *                                commit IDs are swapped.
	 */
	public void addResult(String commitID1, String commitID2, Boolean expectedResult, boolean overrideResultIfPresent) {
		if (commitID1.equals(commitID2))
			return;

		var duplEntry1 = this.getEntryFor(commitID1, commitID2);
		if (overrideResultIfPresent && duplEntry1 != null) {
			if (duplEntry1.expectedResultEquals(expectedResult)) {
				return;
			}
			this.similarityResults.remove(duplEntry1);
		}

		var duplEntry2 = this.getEntryFor(commitID2, commitID1);
		if (overrideResultIfPresent && duplEntry2 != null) {
			if (duplEntry2.expectedResultEquals(expectedResult)) {
				return;
			}
			this.similarityResults.remove(duplEntry2);
		}

		if (overrideResultIfPresent || (duplEntry1 == null && duplEntry2 == null)) {
			this.similarityResults.add(new SimilarityResultEntry(commitID1, commitID2, expectedResult));
		}
	}

	/**
	 * Adds the expected similarity result denoted by the parameters, overrides any
	 * potentially existing result.
	 * 
	 * @see #addResult(String, String, Boolean, boolean)
	 */
	public void addResult(String commitID1, String commitID2, Boolean expectedResult) {
		this.addResult(commitID1, commitID2, expectedResult, true);
	}

	/**
	 * @return The expected similarity result of the commits with the given IDs.
	 *         Returns null, if there is either no entry for the given commit IDs,
	 *         or the entry for the given commit IDs has the expected similarity
	 *         value null.
	 */
	public Boolean getResult(String commitID1, String commitID2) {
		if (commitID1.equals(commitID2))
			return true;

		var entry = this.getEntryFor(commitID1, commitID2);
		if (entry != null) {
			return entry.getExpectedResult();
		} else {
			entry = this.getEntryFor(commitID2, commitID1);
			return entry != null ? entry.getExpectedResult() : null;
		}
	}

	/**
	 * Removes the expected similarity result for the given commit IDs.
	 */
	public void removeResult(String commitID1, String commitID2) {
		var entry = this.getEntryFor(commitID1, commitID2);
		if (entry != null) {
			this.similarityResults.remove(entry);
		}
	}

	/**
	 * Replaces the expected similarity result for the given commit IDs with the
	 * given expectedResult.
	 */
	public void replaceResult(String commitID1, String commitID2, Boolean expectedResult) {
		this.addResult(commitID1, commitID2, expectedResult);
	}

	/**
	 * Assumptions:
	 * <ul>
	 * <li>Same commits are similar with respect to similarity checking
	 * <li>Similarity is symmetric (i.e. swapping commitID1 and commitID2 does not
	 * change the similarity result)
	 * </ul>
	 * 
	 * @param commitID1 The left hand side commit
	 * @param commitID2 The right hand side commit
	 * @return Whether the expected similarity result for the given commits can be
	 *         determined by the contents of this cache
	 */
	public boolean isResultInCache(String commitID1, String commitID2) {
		if (commitID1.equals(commitID2))
			return true;

		return this.getEntryFor(commitID1, commitID2) != null || this.getEntryFor(commitID2, commitID1) != null;
	}

	/**
	 * Removes all expected similarity results saved in this instance.
	 */
	public void clear() {
		this.similarityResults.clear();
	}

	/**
	 * @return The entry for the commits with the given commit IDs.
	 */
	protected SimilarityResultEntry getEntryFor(String commitID1, String commitID2) {
		var entryOpt = this.similarityResults.stream().filter((e) -> e.isEntryFor(commitID1, commitID2)).findFirst();
		if (entryOpt.isPresent()) {
			return entryOpt.get();
		} else {
			return null;
		}
	}

	private class SimilarityResultEntry {
		private final String lhsCommitID;
		private final String rhsCommitID;
		private final Boolean expectedResult;

		private SimilarityResultEntry(String lhsCommitID, String rhsCommitID, Boolean expectedResult) {
			this.lhsCommitID = lhsCommitID;
			this.rhsCommitID = rhsCommitID;
			this.expectedResult = expectedResult;
		}

		public String getLhsCommitID() {
			return lhsCommitID;
		}

		public String getRhsCommitID() {
			return rhsCommitID;
		}

		public Boolean getExpectedResult() {
			return expectedResult;
		}

		public boolean isEntryFor(String lhsCommitID, String rhsCommitID) {
			return this.getLhsCommitID().equals(lhsCommitID) && this.getRhsCommitID().equals(rhsCommitID);
		}

		public boolean expectedResultEquals(Boolean expectedResult) {
			var thisRes = this.getExpectedResult();

			if (thisRes == expectedResult) {
				return true;
			} else if (thisRes == null ^ expectedResult == null) {
				return false;
			} else {
				return thisRes.equals(expectedResult);
			}
		}
	}
}
