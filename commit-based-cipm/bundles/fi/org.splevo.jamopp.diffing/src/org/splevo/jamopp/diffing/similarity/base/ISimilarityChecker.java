package org.splevo.jamopp.diffing.similarity.base;

import org.eclipse.emf.ecore.EObject;

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
	public Boolean isSimilar(EObject element1, EObject element2);
}