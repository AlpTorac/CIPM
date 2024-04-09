package org.splevo.jamopp.diffing.similarity.handlers;

import org.splevo.jamopp.diffing.similarity.requests.ISimilarityRequest;

public interface ISimilarityRequestHandler {
	public Object handleSimilarityRequest(ISimilarityRequest req);
}
