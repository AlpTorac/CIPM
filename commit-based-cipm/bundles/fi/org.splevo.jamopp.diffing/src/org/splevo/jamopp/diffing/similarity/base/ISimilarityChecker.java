package org.splevo.jamopp.diffing.similarity.base;

import java.util.Collection;

public interface ISimilarityChecker {
	/**
	 * Check two objects if they are similar.
	 *
	 * @param element1
	 *            The first element to check.
	 * @param element2
	 *            The second element to check.
	 * @return TRUE, if they are similar; FALSE if not, NULL if it can't be decided.
	 */
	public Boolean isSimilar(Object element1, Object element2);
	
	/**
	 * Check two object lists if they are similar.
	 *
	 * The elements is compared pairwise and it is the responsibility of the provided list
	 * implementations to return them in an appropriate order by calling get(i) with a increasing
	 * index i.
	 *
	 * @return TRUE, if they are all similar; FALSE if a different number of elements is submitted
	 * or at least one pair of elements is not similar to each other.
	 */
	public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2);
}