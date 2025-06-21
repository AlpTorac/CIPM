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

	private RepoTestResultCache cache;

	@BeforeEach
	public void setUp() {
		cache = new RepoTestResultCache();
	}

//	private void addResult(String commitID1, String commitID2, Boolean expectedResult) {
//		cache.addResult(commitID1, commitID2, expectedResult);
//		this.testResult(commitID1, commitID2, !commitID1.equals(commitID2),
//				commitID1.equals(commitID2) ? true : expectedResult,
//				commitID1.equals(commitID2) ? false : true);
//	}
//
//	private void removeResult(String commitID1, String commitID2, boolean expectedResultComputabilityViaTransitivity) {
//		cache.removeResult(commitID1, commitID2);
//		this.testResult(commitID1, commitID2, commitID1.equals(commitID2),
//				commitID1.equals(commitID2) ? true : null, expectedResultComputabilityViaTransitivity);
//	}

//	private void testResult(String commitID1, String commitID2, boolean expectedIsInCacheResult,
//			Boolean expectedSimilarityResult, boolean expectedResultComputabilityViaTransitivity) {
//		var is12InCache = cache.isResultDirectlyComputable(commitID1, commitID2);
//		var is21InCache = cache.isResultDirectlyComputable(commitID2, commitID1);
//
//		Assertions.assertEquals(expectedIsInCacheResult, is12InCache);
//		Assertions.assertEquals(expectedIsInCacheResult, is21InCache);
//		Assertions.assertEquals(is12InCache, is21InCache);
//
//		var is12TransResultComputable = cache.isResultComputableViaTransitivity(commitID1, commitID2);
//		var is21TransResultComputable = cache.isResultComputableViaTransitivity(commitID2, commitID1);
//
//		Assertions.assertEquals(expectedResultComputabilityViaTransitivity, is12TransResultComputable);
//		Assertions.assertEquals(expectedResultComputabilityViaTransitivity, is21TransResultComputable);
//		Assertions.assertEquals(is12TransResultComputable, is21TransResultComputable);
//
//		var is12ResultComputable = cache.isResultComputable(commitID1, commitID2);
//		var is21ResultComputable = cache.isResultComputable(commitID2, commitID1);
//		Assertions.assertEquals(is12InCache || is12TransResultComputable, is12ResultComputable);
//		Assertions.assertEquals(is21InCache || is21TransResultComputable, is21ResultComputable);
//		Assertions.assertEquals(is12ResultComputable, is21ResultComputable);
//
//		var transResult12 = cache.getTransitiveResult(commitID1, commitID2);
//		var transResult21 = cache.getTransitiveResult(commitID2, commitID1);
//		var expectedTransitiveResult = expectedResultComputabilityViaTransitivity ? true : null;
//		Assertions.assertEquals(expectedTransitiveResult, transResult12);
//		Assertions.assertEquals(expectedTransitiveResult, transResult21);
//		Assertions.assertEquals(transResult12, transResult21);
//
//		Assertions.assertEquals(expectedIsInCacheResult ? expectedSimilarityResult : transResult12,
//				cache.getResult(commitID1, commitID2));
//		Assertions.assertEquals(expectedIsInCacheResult ? expectedSimilarityResult : transResult21,
//				cache.getResult(commitID2, commitID1));
//		Assertions.assertEquals(cache.getResult(commitID1, commitID2), cache.getResult(commitID2, commitID1));
//	}

	private void testCacheResult(String commitID1, String commitID2, Boolean expectedSimilarityResult) {
		Assertions.assertEquals(expectedSimilarityResult, cache.getResult(commitID1, commitID2));
		Assertions.assertEquals(expectedSimilarityResult, cache.getResult(commitID2, commitID1));
	}

	private void testIsInCache(String commitID1, String commitID2, boolean shouldBeInCache) {
		Assertions.assertEquals(shouldBeInCache, cache.isInCache(commitID1, commitID2));
		Assertions.assertEquals(shouldBeInCache, cache.isInCache(commitID2, commitID1));
	}

	@Test
	public void addResultTest_True() {
		cache.addResult(cID1, cID2, true);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_False() {
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, false);
	}

	@Test
	public void addResultTest_NoOverride() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID1, cID2, false, false);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_NoOverrideSymmetry() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID1, false, false);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, true);
	}

	@Test
	public void addResultTest_Override() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, false);
	}

	@Test
	public void addResultTest_OverrideSymmetry() {
		cache.addResult(cID1, cID2, true);
		cache.addResult(cID2, cID1, false);
		this.testIsInCache(cID1, cID2, true);
		this.testCacheResult(cID1, cID2, false);
	}

	@Test
	public void removeResultTest() {
		cache.addResult(cID1, cID2, true);
		cache.removeResult(cID1, cID2);
		this.testIsInCache(cID1, cID2, false);
		this.testCacheResult(cID1, cID2, null);
	}

	@Test
	public void removeResultTest_Symmetry() {
		cache.addResult(cID1, cID2, true);
		cache.removeResult(cID2, cID1);
		this.testIsInCache(cID1, cID2, false);
		this.testCacheResult(cID1, cID2, null);
	}

	@Test
	public void reflexivityTest_NoEntries() {
		this.testIsInCache(cID1, cID1, false);
		this.testCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithEntryAddAttempt() {
		cache.addResult(cID1, cID1, true);
		this.testIsInCache(cID1, cID1, false);
		this.testCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithWrongEntryAddAttempt() {
		cache.addResult(cID1, cID1, false);
		this.testIsInCache(cID1, cID1, false);
		this.testCacheResult(cID1, cID1, true);
	}

	@Test
	public void reflexivityTest_WithRemoveAttempt() {
		cache.removeResult(cID1, cID1);
		this.testIsInCache(cID1, cID1, false);
		this.testCacheResult(cID1, cID1, true);
	}

	@Test
	public void transitivityTest_NoReflexivity() {
		this.testIsInCache(cID1, cID1, false);
		Assertions.assertTrue(cache.getTransitiveResult(cID1, cID1));
	}

	@Test
	public void transitivityTest_TwoCommits_Similar() {
		cache.addResult(cID1, cID2, true);
		this.testIsInCache(cID1, cID2, true);
		Assertions.assertTrue(cache.getTransitiveResult(cID1, cID2));
	}

	@Test
	public void transitivityTest_TwoCommits_NonSimilar() {
		cache.addResult(cID1, cID2, false);
		this.testIsInCache(cID1, cID2, true);
		Assertions.assertNull(cache.getTransitiveResult(cID1, cID2));
	}

	@Test
	public void transitivityTest_CommitChain_AllCommitsSimilar() {
		var commitIDs = new String[] { cID1, cID2, cID3, cID4, cID5 };
		for (int i = 0; i < commitIDs.length - 1; i++) {
			cache.addResult(commitIDs[i], commitIDs[i + 1], true);
		}

		for (int i = 0; i < commitIDs.length; i++) {
			for (int j = 0; j < commitIDs.length; j++) {
				this.testIsInCache(commitIDs[i], commitIDs[j], i == j + 1 || j == i + 1);
				Assertions.assertEquals(Boolean.TRUE, cache.getTransitiveResult(commitIDs[i], commitIDs[j]));
				Assertions.assertEquals(Boolean.TRUE, cache.getTransitiveResult(commitIDs[j], commitIDs[i]));
				this.testCacheResult(commitIDs[i], commitIDs[j], Boolean.TRUE);
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
					var brokenEntryOutsideSubChain = (i < brokenEntryIdx + 1 && j < brokenEntryIdx + 1)
							|| (i > brokenEntryIdx && j > brokenEntryIdx);
					this.testIsInCache(commitIDs[i], commitIDs[j], i == j + 1 || j == i + 1);
					Assertions.assertEquals((brokenEntryOutsideSubChain || i == j) ? Boolean.TRUE : null,
							cache.getTransitiveResult(commitIDs[i], commitIDs[j]));
					Assertions.assertEquals((brokenEntryOutsideSubChain || i == j) ? Boolean.TRUE : null,
							cache.getTransitiveResult(commitIDs[j], commitIDs[i]));
				}
			}

			cache.clear();
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
		this.testCacheResult(cID1, cID2, null);
		this.testCacheResult(cID2, cID3, null);
		this.testCacheResult(cID3, cID4, null);
	}
}
