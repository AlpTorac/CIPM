package cipm.consistency.fitests.similarity.java.eobject.unittests.concepttests.dummy;

import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class DummySimilarityToolboxBuilder extends AbstractSimilarityToolboxBuilder {
	@Override
	public DummySimilarityToolboxBuilder instantiate() {
		return (DummySimilarityToolboxBuilder) super.instantiate();
	}
	
	@Override
	public DummySimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req,
			ISimilarityRequestHandler srh) {
		return (DummySimilarityToolboxBuilder) super.buildRequestHandlerPair(req, srh);
	}
	
	public DummySimilarityToolboxBuilder buildEqualsCheckHandler() {
		return (DummySimilarityToolboxBuilder) this.buildRequestHandlerPair(EqualsCheckRequest.class,
				new EqualsCheckRequestHandler());
	}
	
	public DummySimilarityToolboxBuilder buildReferenceCheckHandler() {
		return (DummySimilarityToolboxBuilder) this.buildRequestHandlerPair(ReferenceCheckRequest.class,
				new ReferenceCheckRequestHandler());
	}
}
