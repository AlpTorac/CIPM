package org.splevo.jamopp.diffing.similarity.handlers;

import org.splevo.jamopp.diffing.similarity.ISimilarityComparer;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.requests.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;

public class NewSimilaritySwitchHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public NewSimilaritySwitchHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	@Override
	public SimilaritySwitch handleSimilarityRequest(ISimilarityRequest req) {
		NewSimilaritySwitchRequest castedR = (NewSimilaritySwitchRequest) req;
		var csp = castedR.getParams();
		return new SimilaritySwitch(this.srh, csp);
	}
}
