package cipm.consistency.fitests.similarity.java.utils;

import org.splevo.jamopp.diffing.similarity.JavaSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

public class DummySimilarityToolboxBuilder extends JavaSimilarityToolboxBuilder {
	private InnerSwitchFactory switchFac;
	
	@Override
	public DummySimilarityToolboxBuilder instantiate() {
		return (DummySimilarityToolboxBuilder) super.instantiate();
	}

	@Override
	public DummySimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req, ISimilarityRequestHandler srh) {
		return (DummySimilarityToolboxBuilder) super.buildRequestHandlerPair(req, srh);
	}
	
	@Override
	public DummySimilarityToolboxBuilder buildNewSimilaritySwitchHandler() {
		if (this.getSwitchFactory() != null) {
			return this.buildRequestHandlerPair(NewSimilaritySwitchRequest.class,
					new NewDummySimilaritySwitchHandler(this.getCurrentToolbox(), this.getSwitchFactory()));
		} else {
			return (DummySimilarityToolboxBuilder) super.buildNewSimilaritySwitchHandler();
		}
	}

	public InnerSwitchFactory getSwitchFactory() {
		return this.switchFac;
	}

	public void setSwitchFactory(InnerSwitchFactory switchFac) {
		this.switchFac = switchFac;
	}
}
