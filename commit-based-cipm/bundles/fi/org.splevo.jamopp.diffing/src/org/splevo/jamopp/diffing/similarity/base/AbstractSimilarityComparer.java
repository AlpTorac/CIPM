package org.splevo.jamopp.diffing.similarity.base;

/**
 * An abstract class for similarity comparers to extend. Complements {@link ISimilarityComparer}
 * with the integration of {@link ISimilarityToolbox}.
 * <br><br>
 * Delegates incoming {@link ISimilarityRequest} instances to its {@link ISimilarityToolbox}.
 * 
 * @author atora
 */
public abstract class AbstractSimilarityComparer implements ISimilarityComparer {
    private ISimilarityToolbox st;

	public AbstractSimilarityComparer(ISimilarityToolbox st) {
		this.st = st;
	}
	
	protected ISimilarityToolbox getSimilarityToolbox() {
		return this.st;
	}
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityToolbox().handleSimilarityRequest(req);
	}
}