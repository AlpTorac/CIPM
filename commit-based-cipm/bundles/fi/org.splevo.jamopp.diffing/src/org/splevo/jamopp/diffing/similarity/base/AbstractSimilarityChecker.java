package org.splevo.jamopp.diffing.similarity.base;

public abstract class AbstractSimilarityChecker implements ISimilarityChecker {

	private ISimilarityComparer sc;

	public AbstractSimilarityChecker(ISimilarityToolbox st) {
		this.sc = this.createSimilarityComparer(st);
	}

	protected ISimilarityComparer getSimilarityComparer() {
		return this.sc;
	}

    protected Object handleSimilarityRequest(ISimilarityRequest req) {
    	return this.getSimilarityComparer().handleSimilarityRequest(req);
    }
	
	protected abstract ISimilarityComparer createSimilarityComparer(ISimilarityToolbox st);
}