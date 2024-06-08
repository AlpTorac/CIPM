package org.splevo.jamopp.diffing.similarity.base;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link ISimilarityToolbox} implementor, that uses a {@link Map} to contain
 * 1 to 1 mappings between {@link ISimilarityRequest} classes and
 * {@link ISimilarityRequestHandler} instances.
 * <br><br>
 *  <b>This means that only one {@link ISimilarityRequestHandler} will process incoming {@link ISimilarityRequest}.</b>
 * 
 * @author atora
 */
public class MapSimilarityToolbox implements ISimilarityToolbox {
	/**
	 * The map that contains ({@link ISimilarityRequest} (class),
	 * {@link ISimilarityRequestHandler} (instance)) pairs.
	 */
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

	/**
	 * Attempts to handle the given {@link ISimilarityRequest} (req) by searching {@link #rhMap} for
	 * a matching {@link ISimilarityRequestHandler} and having it process req.
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		var handler = this.rhMap.get(req.getClass());

		return handler != null ? handler.handleSimilarityRequest(req) : null;
	}
}
