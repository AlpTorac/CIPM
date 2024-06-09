package org.splevo.jamopp.diffing.similarity.base;

/**
 * An abstract class for similarity comparers to extend. Complements
 * {@link ISimilarityComparer} with the integration of
 * {@link ISimilarityToolbox}. <br>
 * <br>
 * Delegates incoming {@link ISimilarityRequest} instances to its
 * {@link ISimilarityToolbox}.
 * 
 * @author atora
 */
public abstract class AbstractSimilarityComparer implements ISimilarityComparer {
	/**
	 * The {@link ISimilarityToolbox}, to which all incoming
	 * {@link ISimilarityRequest} instances should be delegated to.
	 */
	private ISimilarityToolbox st;

	/**
	 * Constructs an instance with a given {@link ISimilarityToolbox}
	 * 
	 * @param st The toolbox, to which all incoming {@link ISimilarityRequest}
	 *           instances will be delegated.
	 */
	public AbstractSimilarityComparer(ISimilarityToolbox st) {
		this.st = st;
	}

	/**
	 * @return {@link #st}
	 */
	protected ISimilarityToolbox getSimilarityToolbox() {
		return this.st;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityToolbox().handleSimilarityRequest(req);
	}
}