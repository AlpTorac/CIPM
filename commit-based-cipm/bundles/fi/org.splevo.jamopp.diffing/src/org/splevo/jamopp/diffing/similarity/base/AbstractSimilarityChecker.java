package org.splevo.jamopp.diffing.similarity.base;

public abstract class AbstractSimilarityChecker implements ISimilarityChecker {

	private ISimilarityComparer sc;

	public AbstractSimilarityChecker(ISimilarityToolbox st) {
		this.sc = this.createSimilarityComparer(st);
	}

	protected ISimilarityComparer getSimilarityComparer() {
		return this.sc;
	}

	protected abstract ISimilarityComparer createSimilarityComparer(ISimilarityToolbox st);
}