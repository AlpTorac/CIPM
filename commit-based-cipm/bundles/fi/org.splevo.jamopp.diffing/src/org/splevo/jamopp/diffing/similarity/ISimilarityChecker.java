package org.splevo.jamopp.diffing.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface ISimilarityChecker {
	public ISimilarityComparer getSimilarityComparer();
	
	public ISimilarityComparer createSimilarityComparer();
	
	/**
	 * Check two object lists if they are similar.
	 *
	 * The elements is compared pairwise and it is the responsibility of the provided list
	 * implementations to return them in an appropriate order by calling get(i) with a increasing
	 * index i.
	 *
	 * @param elements1
	 *            The first list of elements to check.
	 * @param elements2
	 *            The second list of elements to check.
	 * @return TRUE, if they are all similar; FALSE if a different number of elements is submitted or at least one pair of elements is not similar to each other.
	 */
	public default Boolean areSimilar(List<? extends EObject> elements1, List<? extends EObject> elements2) {
		return this.getSimilarityComparer().areSimilar(elements1, elements2);
	}

	/**
	 * Check two objects if they are similar.
	 *
	 * @param element1
	 *            The first element to check.
	 * @param element2
	 *            The second element to check.
	 * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
	 */
	public default Boolean isSimilar(EObject element1, EObject element2) {
		return this.getSimilarityComparer().isSimilar(element1, element2);
	}

	/**
	 * Check two objects if they are similar.
	 *
	 * @param element1
	 *            The first element to check.
	 * @param element2
	 *            The second element to check.
	 * @param checkStatementPosition
	 *            Flag if the position of statement elements should be considered or not.
	 * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
	 */
	public default Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {
		return this.getSimilarityComparer().isSimilar(element1, element2, checkStatementPosition);
	}
}