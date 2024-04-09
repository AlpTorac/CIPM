package org.splevo.jamopp.diffing.similarity.base;

import java.util.HashMap;
import java.util.Map;

public class MapSimilarityToolbox implements ISimilarityToolbox {
	private Map<Class<? extends ISimilarityRequest>, ISimilarityRequestHandler> rhMap;
	
	public MapSimilarityToolbox() {
		this.rhMap = new HashMap<Class<? extends ISimilarityRequest>, ISimilarityRequestHandler>();
	}
	
	@Override
	public void addRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass, ISimilarityRequestHandler srh) {
		this.rhMap.put(reqClass, srh);
	}

	@Override
	public void removeRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass) {
		this.rhMap.remove(reqClass);
	}

	@Override
	public void clearRequestHandlerPairs() {
		this.rhMap.clear();
	}

	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var handler = this.rhMap.get(req.getClass());

		return handler != null ? handler.handleSimilarityRequest(req) : null;
	}
}
