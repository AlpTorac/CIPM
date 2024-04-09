package org.splevo.jamopp.diffing.similarity.base;

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