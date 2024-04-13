package cipm.consistency.fitests.similarity.java.utils;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class NewDummySimilaritySwitchHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	private InnerSwitchFactory switchFac;
	
	public NewDummySimilaritySwitchHandler(ISimilarityRequestHandler srh, InnerSwitchFactory switchFac) {
		this.srh = srh;
		this.switchFac = switchFac;
	}
	
	@Override
	public DummySimilaritySwitch handleSimilarityRequest(ISimilarityRequest req) {
		var ss = new DummySimilaritySwitch(this.srh);
		var innerSwitches = this.switchFac.createSwitchesFor(ss);
		ss.setSwitches(innerSwitches);
		return ss;
	}
}
