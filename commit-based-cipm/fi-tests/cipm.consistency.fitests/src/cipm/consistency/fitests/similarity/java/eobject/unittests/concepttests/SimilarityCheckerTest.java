package cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.DummySimilarityChecker;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.DummySimilarityToolbox;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.DummySingleSimilarityRequest;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.DummySingleSimilarityRequestHandler;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.EqualsCheckRequestHandler;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.ReferenceCheckRequest;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.ReferenceCheckRequestHandler;
import cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy.DummySimilarityToolbox.HandleHistoryEntry;

public class SimilarityCheckerTest extends AbstractDummySimilarityCheckingTest {
	@Test
	public void testHandleSimilarityRequest() {
		var tb = new DummySimilarityToolbox();
		var dssh = new DummySingleSimilarityRequestHandler(tb);
		var ecrh = new EqualsCheckRequestHandler();
		var rcrh = new ReferenceCheckRequestHandler();
		
		tb.addRequestHandlerPair(DummySingleSimilarityRequest.class, dssh);
		tb.addRequestHandlerPair(EqualsCheckRequest.class, ecrh);
		tb.addRequestHandlerPair(ReferenceCheckRequest.class, rcrh);
		
		var sc = new DummySimilarityChecker(tb);
		
		Assertions.assertFalse(sc.isSimilar(1, 2));
		
		var history = tb.getHandlingHistory();
		Assertions.assertEquals(3, history.size());
		
		var dsHistoryArr = history.stream()
				.filter((e) -> e.getHandler().equals(dssh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, dsHistoryArr.length);
		var dsHistory = dsHistoryArr[0];
		
		var rcHistoryArr = history.stream()
				.filter((e) -> e.getHandler().equals(rcrh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, rcHistoryArr.length);
		var rcHistory = rcHistoryArr[0];
		
		var ecHistoryArr = history.stream()
				.filter((e) -> e.getHandler().equals(ecrh))
				.toArray(HandleHistoryEntry[]::new);
		Assertions.assertEquals(1, ecHistoryArr.length);
		var ecHistory = ecHistoryArr[0];
		
		Assertions.assertEquals(Boolean.FALSE, dsHistory.getOutput());
		Assertions.assertEquals(Boolean.FALSE, rcHistory.getOutput());
		Assertions.assertEquals(Boolean.FALSE, ecHistory.getOutput());
	}
}
