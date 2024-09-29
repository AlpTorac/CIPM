package cipm.consistency.fitests.similarity.base.dummy.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.base.dummy.DummySimilarityChecker;
import cipm.consistency.fitests.similarity.base.dummy.DummySimilarityToolbox;
import cipm.consistency.fitests.similarity.base.dummy.DummySingleSimilarityRequest;
import cipm.consistency.fitests.similarity.base.dummy.DummySingleSimilarityRequestHandler;
import cipm.consistency.fitests.similarity.base.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.base.dummy.EqualsCheckRequestHandler;
import cipm.consistency.fitests.similarity.base.dummy.ReferenceCheckRequest;
import cipm.consistency.fitests.similarity.base.dummy.ReferenceCheckRequestHandler;
import cipm.consistency.fitests.similarity.base.dummy.DummySimilarityToolbox.HandleHistoryEntry;

public class SimilarityCheckerTest extends AbstractDummySimilarityCheckingTest {
	@Test
	public void testIsSimilar() {
		var tb = new DummySimilarityToolbox();
		var dssh = new DummySingleSimilarityRequestHandler(tb);
		var ecrh = new EqualsCheckRequestHandler();
		var rcrh = new ReferenceCheckRequestHandler();

		tb.addRequestHandlerPair(DummySingleSimilarityRequest.class, dssh);
		tb.addRequestHandlerPair(EqualsCheckRequest.class, ecrh);
		tb.addRequestHandlerPair(ReferenceCheckRequest.class, rcrh);

		var sc = new DummySimilarityChecker(tb);

		// Check if primitive integers 1 and 2 are similar
		Assertions.assertFalse(sc.isSimilar(1, 2));

		var history = tb.getHandlingHistory();

		/*
		 * 3 history entries are expected, since DummySingleSimilarityRequest results in
		 * a EqualsCheckRequest and a ReferenceCheckRequest. All in all, one history
		 * entry from each handler above is expected.
		 */
		Assertions.assertEquals(3, history.size());

		var dsHistoryArr = history.stream().filter((e) -> e.getHandler().equals(dssh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, dsHistoryArr.length);
		var dsHistory = dsHistoryArr[0];

		var rcHistoryArr = history.stream().filter((e) -> e.getHandler().equals(rcrh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, rcHistoryArr.length);
		var rcHistory = rcHistoryArr[0];

		var ecHistoryArr = history.stream().filter((e) -> e.getHandler().equals(ecrh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, ecHistoryArr.length);
		var ecHistory = ecHistoryArr[0];

		Assertions.assertEquals(Boolean.FALSE, dsHistory.getOutput());
		Assertions.assertEquals(Boolean.FALSE, rcHistory.getOutput());
		Assertions.assertEquals(Boolean.FALSE, ecHistory.getOutput());
	}
}
