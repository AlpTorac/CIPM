package org.splevo.jamopp.diffing.similarity.base;

/**
 * An interface for encapsulating similarity operations, whose implementors take
 * {@link ISimilarityRequest} instances and process them. Each similarity
 * request handler is meant to have a matching request. <br>
 * <br>
 * If the underlying similarity operation uses parameters that are not changed
 * much during the similarity checking process, they can be encapsulated here as
 * well. <br>
 * <br>
 * Implementors can contain further {@link ISimilarityRequestHandler} instances,
 * if they have to process requests, which need further processing afterward.
 * They can then delegate the request either them or their containing
 * {@link ISimilarityToolbox}.
 * 
 * @author atora
 */
public interface ISimilarityRequestHandler {
	/**
	 * Processes the given {@link ISimilarityRequest} and returns the result. <br>
	 * <br>
	 * It is recommended to not override the return type, especially if the concrete
	 * {@link ISimilarityRequestHandler} instances are planned to be extended.
	 * 
	 * @param req The incoming request.
	 */
	public Object handleSimilarityRequest(ISimilarityRequest req);

	/**
	 * @return Whether the given {@link ISimilarityRequest} instance can be handled.
	 */
	public default boolean canHandleSimilarityRequest(ISimilarityRequest req) {
		return this.canHandleSimilarityRequest(req.getClass());
	}

	/**
	 * @return Whether the given {@link ISimilarityRequest} class can be handled.
	 */
	public boolean canHandleSimilarityRequest(Class<? extends ISimilarityRequest> reqClass);
}
