package cipm.consistency.fitests.repositorytests.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RepoTestResultCacheTest {
	private static final String cID1 = "cID1";
	private static final String cID2 = "cID2";
	private static final String cID3 = "cID3";
	private static final String cID4 = "cID4";
	private static final String cID5 = "cID5";
	private static final String cID6 = "cID6";

	private RepoTestResultCache cache;

	@BeforeEach
	public void setUp() {
		cache = new RepoTestResultCache();
	}

	private void testDirectCacheResult(String commitID1, String commitID2, Boolean expectedSimilarityResult) {
		Assertions.assertEquals(Boolean.TRUE, cache.getDirectResult(commitID1, commitID1));
		Assertions.assertEquals(Boolean.TRUE, cache.getDirectResult(commitID2, commitID2));

		Assertions.assertEquals(expectedSimilarityResult, cache.getDirectResult(commitID1, commitID2));
		Assertions.assertEquals(expectedSimilarityResult, cache.getDirectResult(commitID2, commitID1));
	}

	private void testIsInCache(String commitID1, String commitID2, boolean shouldBeInCache) {
		Assertions.assertEquals(false, cache.isInCache(commitID1, commitID1));
		Assertions.assertEquals(false, cache.isInCache(commitID2, commitID2));

		Assertions.assertEquals(shouldBeInCache, cache.isInCache(commitID1, commitID2));
		Assertions.assertEquals(shouldBeInCache, cache.isInCache(commitID2, commitID1));
	}

	private void testTransitiveCacheResult(String commitID1, String commitID2, Boolean expectedTransitiveResult) {
		Assertions.assertEquals(expectedTransitiveResult, cache.getTransitiveResult(commitID1, commitID2));
		Assertions.assertEquals(expectedTransitiveResult, cache.getTransitiveResult(commitID2, commitID1));
	}

	private void testCacheResult(String commitID1, String commitID2, Boolean expectedDirectSimilarityResult,
			Boolean expectedTransitiveResult) {
		this.testDirectCacheResult(commitID1, commitID2, expectedDirectSimilarityResult);
		this.testTransitiveCacheResult(commitID1, commitID2, expectedTransitiveResult);

		var expectedResult = expectedDirectSimilarityResult;
		if (expectedResult == null)
			expectedResult = expectedTransitiveResult;

		Assertions.assertEquals(expectedResult, cache.getResult(commitID1, commitID2));
	}

	@Test
	public void addResultTest_True() {
		cache.addResult(cID1, cID2, true);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_False() {
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, false);
	}

	@Test
	public void addResultTest_NoOverride() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID1, cID2, false, false);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_NoOverrideSymmetry() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID1, false, false);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_Override() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, false);
	}

	@Test
	public void addResultTest_OverrideSymmetry() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID1, false);
		this.testIsInCache(cID1, cID2, true);
		this.testDirectCacheResult(cID1, cID2, false);
	}

	@Test
	public void removeResultTest() {
		cache.addResult(cID1, cID2, true);
		cache.removeResult(cID1, cID2);
		this.testIsInCache(cID1, cID2, false);
		this.testDirectCacheResult(cID1, cID2, null);
	}

	@Test
	public void removeResultTest_Symmetry() {
		cache.addResult(cID1, cID2, true);
		cache.removeResult(cID2, cID1);
		this.testIsInCache(cID1, cID2, false);
		this.testDirectCacheResult(cID1, cID2, null);
	}

	@Test
	public void reflexivityTest_NoEntries() {
		this.testIsInCache(cID1, cID1, false);
		this.testDirectCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithEntryAddAttempt() {
		cache.addResult(cID1, cID1, true);
		this.testIsInCache(cID1, cID1, false);
		this.testDirectCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithWrongEntryAddAttempt() {
		cache.addResult(cID1, cID1, false);
		this.testIsInCache(cID1, cID1, false);
		this.testDirectCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithRemoveAttempt() {
		cache.removeResult(cID1, cID1);
		this.testIsInCache(cID1, cID1, false);
		this.testDirectCacheResult(cID1, cID1, true);
	}

	@Test
	public void transitivityTest_Reflexivity() {
		this.testIsInCache(cID1, cID1, false);
		Assertions.assertTrue(cache.getTransitiveResult(cID1, cID1));
	}

	@Test
	public void transitivityTest_TwoCommits_Similar() {
		cache.addResult(cID1, cID2, true);
		this.testIsInCache(cID1, cID2, true);
		this.testTransitiveCacheResult(cID1, cID2, Boolean.TRUE);
	}

	@Test
	public void transitivityTest_TwoCommits_NonSimilar() {
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		this.testTransitiveCacheResult(cID1, cID2, null);
	}

	@Test
	public void transitivityTest_CommitChain_AllCommitsSimilar() {
		var commitIDs = new String[] { cID1, cID2, cID3, cID4, cID5 };
		for (int i = 0; i < commitIDs.length - 1; i++) {
			cache.addResult(commitIDs[i], commitIDs[i + 1], true);
		}

		for (int i = 0; i < commitIDs.length; i++) {
			for (int j = 0; j < commitIDs.length; j++) {
				var areCommitIDsAdjadent = i == j + 1 || j == i + 1;

				this.testIsInCache(commitIDs[i], commitIDs[j], i == j + 1 || j == i + 1);
				this.testCacheResult(commitIDs[i], commitIDs[j], (areCommitIDsAdjadent || i == j) ? Boolean.TRUE : null,
						Boolean.TRUE);
			}
		}
	}

	@Test
	public void transitivityTest_CommitChain_SimilarityBroken() {
		var commitIDs = new String[] { cID1, cID2, cID3, cID4, cID5 };
		for (int brokenEntryIdx = 0; brokenEntryIdx < commitIDs.length - 1; brokenEntryIdx++) {
			for (int i = 0; i < commitIDs.length - 1; i++) {
				cache.addResult(commitIDs[i], commitIDs[i + 1], true);
			}

			cache.addResult(commitIDs[brokenEntryIdx], commitIDs[brokenEntryIdx + 1], false);

			for (int i = 0; i < commitIDs.length; i++) {
				for (int j = 0; j < commitIDs.length; j++) {
					var brokenEntryReached = (i == brokenEntryIdx && j == brokenEntryIdx + 1)
							|| (i == brokenEntryIdx + 1 && j == brokenEntryIdx);

					var brokenEntryOutsideSubChain = (i < brokenEntryIdx + 1 && j < brokenEntryIdx + 1)
							|| (i > brokenEntryIdx && j > brokenEntryIdx);

					var areCommitIDsAdjadent = i == j + 1 || j == i + 1;

					this.testIsInCache(commitIDs[i], commitIDs[j], areCommitIDsAdjadent);
					this.testCacheResult(commitIDs[i], commitIDs[j],
							(areCommitIDsAdjadent || i == j) ? !brokenEntryReached : null,
							(brokenEntryOutsideSubChain || i == j) ? Boolean.TRUE : null);
				}
			}

			cache.clear();
		}
	}

	@Test
	public void transitivityTest_Hexagon_AllSimilar() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID3, true);
		cache.addResult(cID3, cID6, true);

		cache.addResult(cID1, cID4, true);
		cache.addResult(cID4, cID5, true);
		cache.addResult(cID5, cID6, true);

		for (var id : new String[] { cID1, cID2, cID3, cID4, cID5, cID6 }) {
			this.testTransitiveCacheResult(id, cID6, Boolean.TRUE);
		}
	}

	@Test
	public void transitivityTest_Hexagon_SimilarityBroken() {
		cache.addResult(cID1, cID2, false);
		cache.addResult(cID2, cID3, true);
		cache.addResult(cID3, cID6, true);

		cache.addResult(cID1, cID4, true);
		cache.addResult(cID4, cID5, false);
		cache.addResult(cID5, cID6, true);

		for (var id : new String[] { cID1, cID4 }) {
			this.testTransitiveCacheResult(id, cID6, null);
		}
	}

	@Test
	public void transitivityTest_Hexagon_SimilarityBrokenOnOneSide() {
		cache.addResult(cID1, cID2, false);
		cache.addResult(cID2, cID3, true);
		cache.addResult(cID3, cID6, true);

		cache.addResult(cID1, cID4, true);
		cache.addResult(cID4, cID5, true);
		cache.addResult(cID5, cID6, true);

		for (var id : new String[] { cID1, cID2, cID3, cID4, cID5, cID6 }) {
			this.testTransitiveCacheResult(id, cID6, Boolean.TRUE);
		}
	}

	@Test
	public void cleanCacheTest() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID3, false);
		cache.clear();
		this.testIsInCache(cID1, cID2, false);
		this.testIsInCache(cID2, cID3, false);
		this.testIsInCache(cID3, cID4, false);
		this.testDirectCacheResult(cID1, cID2, null);
		this.testDirectCacheResult(cID2, cID3, null);
		this.testDirectCacheResult(cID3, cID4, null);
	}
}
