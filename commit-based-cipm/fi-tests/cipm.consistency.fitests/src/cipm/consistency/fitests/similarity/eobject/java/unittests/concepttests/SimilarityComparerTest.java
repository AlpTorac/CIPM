package cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.DummySimilarityComparer;
import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.DummySimilarityToolbox;
import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.EqualsCheckRequestHandler;
import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.ReferenceCheckRequest;
import cipm.consistency.fitests.similarity.eobject.java.unittests.concepttests.dummy.ReferenceCheckRequestHandler;

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
		
		Assertions.assertEquals(req, he.getReq());
		Assertions.assertEquals(ecrh, he.getHandler());
		Assertions.assertEquals(result, he.getOutput());
		Assertions.assertEquals(ecrh.handleSimilarityRequest(req), he.getOutput());
	}
}
