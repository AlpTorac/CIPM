package org.splevo.jamopp.diffing.similarity.handlers;

import org.splevo.jamopp.diffing.similarity.JavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

public class NewSimilaritySwitchHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public NewSimilaritySwitchHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		NewSimilaritySwitchRequest castedR = (NewSimilaritySwitchRequest) req;
		Boolean csp = (Boolean) castedR.getParams();
		return new JavaSimilaritySwitch(this.srh, csp);
	}
}
