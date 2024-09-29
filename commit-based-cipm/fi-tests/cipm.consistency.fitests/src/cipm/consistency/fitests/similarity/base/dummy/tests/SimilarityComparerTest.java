package cipm.consistency.fitests.similarity.base.dummy.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.base.dummy.DummySimilarityComparer;
import cipm.consistency.fitests.similarity.base.dummy.DummySimilarityToolbox;
import cipm.consistency.fitests.similarity.base.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.base.dummy.EqualsCheckRequestHandler;
import cipm.consistency.fitests.similarity.base.dummy.ReferenceCheckRequest;
import cipm.consistency.fitests.similarity.base.dummy.ReferenceCheckRequestHandler;

public class SimilarityComparerTest extends AbstractDummySimilarityCheckingTest {
	@Test
	public void testCanHandleSimilarityRequest() {
		var simComparer = new DummySimilarityComparer(this.buildFullToolbox());

		Assertions.assertTrue(simComparer.canHandleSimilarityRequest(EqualsCheckRequest.class));
		Assertions.assertTrue(simComparer.canHandleSimilarityRequest(ReferenceCheckRequest.class));
	}

	@Test
	public void testHandleSimilarityRequest() {
		var tb = new DummySimilarityToolbox();
		var ecrh = new EqualsCheckRequestHandler();
		var rcrh = new ReferenceCheckRequestHandler();

		tb.addRequestHandlerPair(EqualsCheckRequest.class, ecrh);
		tb.addRequestHandlerPair(ReferenceCheckRequest.class, rcrh);

		var simComparer = new DummySimilarityComparer(tb);

		var req = new EqualsCheckRequest(1, 2);
		var result = simComparer.handleSimilarityRequest(req);

		var history = tb.getHandlingHistory();
		Assertions.assertEquals(1, history.size());
		var he = history.get(0);

		Assertions.assertEquals(req, he.getRequest());
		Assertions.assertEquals(ecrh, he.getHandler());
		Assertions.assertEquals(result, he.getOutput());
		Assertions.assertEquals(ecrh.handleSimilarityRequest(req), he.getOutput());
	}
}
