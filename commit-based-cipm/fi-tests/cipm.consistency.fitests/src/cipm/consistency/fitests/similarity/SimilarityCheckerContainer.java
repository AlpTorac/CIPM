package cipm.consistency.fitests.similarity;

import java.util.Collection;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

public class SimilarityCheckerContainer extends AbstractSimilarityCheckerContainer {
	private ISimilarityChecker sc;
	
	public ISimilarityChecker getSimilarityChecker() {
		if (this.sc == null) {
			this.sc = this.getSimilarityCheckerProvider().createSC();
		}

		return this.sc;
	}
	
	@Override
	public void resetSimilarityChecker() {
		this.sc = this.getSimilarityChecker();
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	public Boolean isSimilar(Object element1, Object element2) {
		return this.getSimilarityChecker().isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	public Boolean areSimilar(Collection<Object> elements1,
			Collection<Object> elements2) {
		return this.getSimilarityChecker().areSimilar(elements1, elements2);
	}
}
