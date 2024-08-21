package cipm.consistency.fitests.similarity.java.unittests.concepttests;

import cipm.consistency.fitests.similarity.java.AbstractSimilarityTest;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.DummySimilarityToolbox;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.DummySimilarityToolboxBuilder;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.DummySimilarityToolboxFactory;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.EqualsCheckRequest;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.EqualsCheckRequestHandler;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.ReferenceCheckRequest;
import cipm.consistency.fitests.similarity.java.unittests.concepttests.dummy.ReferenceCheckRequestHandler;

/**
 * An abstract class that contains utility methods for tests that use dummy
 * implementations of similarity checking elements.
 * 
 * @author atora
 */
public abstract class AbstractDummySimilarityCheckingTest extends AbstractSimilarityTest {
	@Override
	protected void logTestEndMessage() {
	}

	/**
	 * Constructs a {@link DummySimilarityToolbox} instance using a
	 * {@link DummySimilarityToolboxBuilder}. Adds request-handler pairs to that
	 * instance and returns it.
	 */
	protected DummySimilarityToolbox buildFullToolbox() {
		var tbBuilder = new DummySimilarityToolboxBuilder();
		tbBuilder.setSimilarityToolboxFactory(new DummySimilarityToolboxFactory());

		return (DummySimilarityToolbox) tbBuilder.instantiate().buildEqualsCheckHandler().buildReferenceCheckHandler()
				.build();
	}

	/**
	 * Constructs a {@link DummySimilarityToolbox} instance without using any
	 * builders. Adds request-handler pairs to that instance and returns it.
	 */
	protected DummySimilarityToolbox createFullToolbox() {
		var tb = new DummySimilarityToolbox();

		tb.addRequestHandlerPair(EqualsCheckRequest.class, new EqualsCheckRequestHandler());
		tb.addRequestHandlerPair(ReferenceCheckRequest.class, new ReferenceCheckRequestHandler());

		return tb;
	}
}