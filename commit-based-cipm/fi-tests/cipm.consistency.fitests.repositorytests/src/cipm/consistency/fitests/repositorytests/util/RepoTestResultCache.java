package cipm.consistency.fitests.repositorytests.util;

import java.util.ArrayList;
import java.util.Collection;

public class RepoTestResultCache {
	private final Collection<SimilarityResultEntry> similarityResults = new ArrayList<SimilarityResultEntry>();

	public void addResult(String commitID1, String commitID2, Boolean expectedResult) {
		if (commitID1.equals(commitID2))
			return;

		var duplEntry1 = this.getEntryFor(commitID1, commitID2);
		if (duplEntry1 != null) {
			if (duplEntry1.expectedResultEquals(expectedResult)) {
				return;
			}
			this.similarityResults.remove(duplEntry1);
		}

		var duplEntry2 = this.getEntryFor(commitID2, commitID1);
		if (duplEntry2 != null) {
			if (duplEntry2.expectedResultEquals(expectedResult)) {
				return;
			}
			this.similarityResults.remove(duplEntry2);
		}

		this.similarityResults.add(new SimilarityResultEntry(commitID1, commitID2, expectedResult));
	}

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

	public void removeResult(String commitID1, String commitID2) {
		var entry = this.getEntryFor(commitID1, commitID2);
		if (entry != null) {
			this.similarityResults.remove(entry);
		}
	}

	public void replaceResult(String commitID1, String commitID2, Boolean expectedResult) {
		this.addResult(commitID1, commitID2, expectedResult);
	}

	public boolean isInCache(String commitID1, String commitID2) {
		return this.getEntryFor(commitID1, commitID2) != null || this.getEntryFor(commitID2, commitID1) != null;
	}

	public void clear() {
		this.similarityResults.clear();
	}

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
