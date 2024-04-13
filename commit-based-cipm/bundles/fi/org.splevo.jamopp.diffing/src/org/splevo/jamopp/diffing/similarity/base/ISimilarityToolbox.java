package org.splevo.jamopp.diffing.similarity.base;

/**
 * An interface for classes that contain ({@link ISimilarityRequest} (class),
 * {@link ISimilarityRequestHandler}) pairs. <br>
 * <br>
 * Since the implementors contain the pairs specified above, they themselves can
 * be used as {@link ISimilarityRequestHandler} to handle
 * {@link ISimilarityRequest} instances. In such cases, they delegate the
 * received {@link ISimilarityRequest} to their matching
 * {@link ISimilarityRequestHandler}. <br>
 * <br>
 * Implementors of this interface allow adding and removing the said pairs
 * dynamically. In doing so, they make dynamic changes to how similarity is
 * computed possible. Additionally, new similarity operations can be integrated
 * without modifying the implementors themselves but by calling the
 * {@link #addRequestHandlerPair(Class, ISimilarityRequestHandler)} method. <br>
 * <br>
 * This interface makes no assumptions on the data structure used for its
 * concrete implementors.
 * 
 * @see {@link ISimilarityToolboxBuilder}, {@link ISimilarityToolboxFactory}
 * @author atora
 */
public interface ISimilarityToolbox extends ISimilarityRequestHandler {
	/**
	 * Adds a ({@link ISimilarityRequest} (class),
	 * {@link ISimilarityRequestHandler}) pair. <br>
	 * <br>
	 * <b>Note that the first parameter (reqClass) is the class of the
	 * {@link ISimilarityRequest} and the second parameter (srh) is an
	 * {@link ISimilarityRequestHandler} instance.</b>
	 */
	public void addRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass, ISimilarityRequestHandler srh);

	/**
	 * Removes the pairs added for {@link ISimilarityRequest}.
	 * 
	 * @see {@link #addRequestHandlerPair(Class, ISimilarityRequestHandler)}
	 */
	public void removeRequestHandlerPair(Class<? extends ISimilarityRequest> reqClass);

	/**
	 * Removes all pairs added to the toolbox.
	 * 
	 * @see {@link #addRequestHandlerPair(Class, ISimilarityRequestHandler)}
	 */
	public void clearRequestHandlerPairs();

	/**
	 * Attempts to handle the given {@link ISimilarityRequest}. <br>
	 * <br>
	 * The underlying data structure can influence the way {@code req} is handled.
	 * For example, in cases where there is no corresponding handler or if there are
	 * multiple corresponding handlers.
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req);
}