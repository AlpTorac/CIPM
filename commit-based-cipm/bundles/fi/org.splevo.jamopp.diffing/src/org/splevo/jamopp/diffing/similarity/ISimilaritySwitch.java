package org.splevo.jamopp.diffing.similarity;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;

public interface ISimilaritySwitch {

	public ISimilarityChecker getSimilarityChecker();
	
	public boolean getDefaultCheckStatementPosition();

	public void setDefaultCheckStatementPosition(boolean defaultCheckStatementPositionFlag);

	public EObject getCompareElement();

	public Boolean compare(EObject eo1, EObject eo2);
	
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
		return this.getSimilarityChecker().areSimilar(elements1, elements2);
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
		return this.isSimilar(element1, element2, this.getDefaultCheckStatementPosition());
	}
	
	public default Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {
		return this.getSimilarityChecker().isSimilar(element1, element2, checkStatementPosition);
	}
	
	public boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2);
}