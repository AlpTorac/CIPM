package cipm.consistency.fitests.similarity.java.unittests.concepttests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.DummySimilarityToolboxBuilder;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.DummySimilarityToolboxFactory;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.ReferenceCheckRequest;

public class SimilarityToolboxBuilderTest extends AbstractDummySimilarityCheckingTest {
	@Test
	public void testBuild() {
		var tbBuilder = new DummySimilarityToolboxBuilder();
		tbBuilder.setSimilarityToolboxFactory(new DummySimilarityToolboxFactory());

		Assertions.assertNull(tbBuilder.build());
		tbBuilder.instantiate();
		Assertions.assertNotNull(tbBuilder.build());
		Assertions.assertNull(tbBuilder.build());
	}

	@Test
	public void testRequestHandlerPairs() {
		var tbBuilder = new DummySimilarityToolboxBuilder();
		tbBuilder.setSimilarityToolboxFactory(new DummySimilarityToolboxFactory());

		var tbNoPairs = tbBuilder.instantiate().build();

		Assertions.assertFalse(tbNoPairs.canHandleSimilarityRequest(EqualsCheckRequest.class));
		Assertions.assertFalse(tbNoPairs.canHandleSimilarityRequest(ReferenceCheckRequest.class));

		var tbOnePair = tbBuilder.instantiate().buildEqualsCheckHandler().build();

		Assertions.assertTrue(tbOnePair.canHandleSimilarityRequest(EqualsCheckRequest.class));
		Assertions.assertFalse(tbOnePair.canHandleSimilarityRequest(ReferenceCheckRequest.class));

		var tbBothPairs = tbBuilder.instantiate().buildEqualsCheckHandler().buildReferenceCheckHandler().build();

		Assertions.assertTrue(tbBothPairs.canHandleSimilarityRequest(EqualsCheckRequest.class));
		Assertions.assertTrue(tbBothPairs.canHandleSimilarityRequest(ReferenceCheckRequest.class));
	}
}
