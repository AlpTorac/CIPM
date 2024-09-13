package cipm.consistency.fitests.similarity;

import java.util.Collection;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;

/**
 * A class that integrates {@link ISimilarityCheckerProvider} with
 * {@link ISimilarityCheckerContainer}.
 * 
 * @author atora
 */
public class SimilarityCheckerContainerWithProvider implements ISimilarityCheckerContainer {
	/**
	 * The {@link ISimilarityCheckerProvider} instance that will be used to generate
	 * similarity checkers.
	 */
	private ISimilarityCheckerProvider scp;

	/**
	 * The similarity checker that will be used for similarity checking.
	 */
	private ISimilarityChecker sc;

	/**
	 * @return A similarity checker instance created with the provider returned by
	 *         {@link #getSimilarityCheckerProvider()}
	 */
	protected ISimilarityChecker createSC() {
		return this.getSimilarityCheckerProvider().createSC();
	}

	/**
	 * Sets the {@link ISimilarityCheckerProvider} instance that will be used to
	 * generate similarity checkers.
	 */
	public void setSimilarityCheckerProvider(ISimilarityCheckerProvider scp) {
		this.scp = scp;
	}

	/**
	 * @return The {@link ISimilarityCheckerProvider} instance that will be used to
	 *         generate similarity checkers.
	 */
	public ISimilarityCheckerProvider getSimilarityCheckerProvider() {
		return this.scp;
	}

	/**
	 * @return The similarity checker that is currently used for similarity
	 *         checking.
	 */
	public ISimilarityChecker getSimilarityChecker() {
		if (this.sc == null) {
			this.sc = this.createSC();
		}

		return this.sc;
	}

	@Override
	public void resetSimilarityChecker() {
		this.sc = this.createSC();
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		return this.getSimilarityChecker().isSimilar(element1, element2);
	}

	/**
	 * Delegates similarity checking to the underlying {@link ISimilarityChecker}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean areSimilar(Collection<?> elements1, Collection<?> elements2) {
		return this.getSimilarityChecker().areSimilar((Collection<Object>) elements1, (Collection<Object>) elements2);
	}
}
