package org.splevo.jamopp.diffing.similarity.base;

public interface ISimilarityToolbox extends ISimilarityRequestHandler {
	public void addRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass, ISimilarityRequestHandler srh);
	public void removeRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass);
	public void clearRequestHandlerPairs();
	
	/**
	 * Attempts to handle the given {@link ISimilarityRequest}.
	 * <br><br>
	 * The underlying data structure can influence the way
	 * {@code req} is handled. For example, in cases where there
	 * is no corresponding handler or
	 * if there are multiple corresponding handlers.
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req);
}
