package org.splevo.jamopp.diffing.similarity.base;

/**
 * An interface for encapsulating parameters required by similarity checking
 * operations. Similarity requests are intended to be used by their matching
 * {@link ISimilarityRequestHandler}.
 * <br><br>
 * It is recommended to not override the return type of {@link #getParams()},
 * especially if the concrete similarity requests are planned to be extended.
 * 
 * @see {@link ISimilarityToolbox}, {@link ISimilarityRequestHandler}
 * @author atora
 */
public interface ISimilarityRequest {
	/**
	 * Returns all parameters encapsulated by this request instance. 
	 */
	public Object getParams();
}
