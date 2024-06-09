package org.splevo.jamopp.diffing.similarity.base;

/**
 * An abstract class for similarity checkers to extend. Complements
 * {@link ISimilarityChecker} with the integration of
 * {@link ISimilarityComparer}. <br>
 * <br>
 * Does not implement {@link ISimilarityRequestHandler}, because its concrete
 * implementors are thought to serve as facades to the outside. They are thus
 * not intended to be used during the similarity checking process, other than a
 * call to the {@link #isSimilar(Object, Object)} method at the start.
 * 
 * @author atora
 */
public abstract class AbstractSimilarityChecker implements ISimilarityChecker {
	/**
	 * The {@link ISimilarityComparer}, to which incoming {@link ISimilarityRequest}
	 * instances are to be delegated.
	 */
	private ISimilarityComparer sc;

	/**
	 * Constructs an {@link AbstractSimilarityChecker} instance and sets its
	 * {@link #sc} by calling {@link #createSimilarityComparer(ISimilarityToolbox)}
	 * and assigning the result to it.
	 * 
	 * @param st The toolbox that {@link #sc} will use.
	 */
	public AbstractSimilarityChecker(ISimilarityToolbox st) {
		this.sc = this.createSimilarityComparer(st);
	}

	/**
	 * Declared as protected only to allow access from concrete implementors.
	 * 
	 * @return {@link #sc}
	 */
	protected ISimilarityComparer getSimilarityComparer() {
		return this.sc;
	}

	/**
	 * Delegates the incoming {@link ISimilarityRequest} to {@link #sc}. <br>
	 * <br>
	 * Declared as protected only to allow access from concrete implementors. This
	 * is necessary, because creating further internal constructs may involve using
	 * {@link ISimilarityRequestHandler} instances and that in return requires
	 * {@link ISimilarityRequest} instances.
	 */
	protected Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityComparer().handleSimilarityRequest(req);
	}

	/**
	 * Creates an {@link ISimilarityComparer} with the given
	 * {@link ISimilarityToolbox}.
	 * 
	 * @param st The toolbox that the constructed {@link ISimilarityComparer} will use.
	 */
	protected abstract ISimilarityComparer createSimilarityComparer(ISimilarityToolbox st);
}