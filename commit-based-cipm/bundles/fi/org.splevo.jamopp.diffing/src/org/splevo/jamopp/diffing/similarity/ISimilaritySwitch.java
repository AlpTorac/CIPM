package org.splevo.jamopp.diffing.similarity;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;

public interface ISimilaritySwitch {

	public boolean getDefaultCheckStatementPosition();

	public void setDefaultCheckStatementPosition(boolean defaultCheckStatementPositionFlag);
	
	public Map<Pattern, String> getClassifierNormalizations();

	public Map<Pattern, String> getCompilationUnitNormalizations();

	public Map<Pattern, String> getPackageNormalizations();

	public EObject getCompareElement();

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
	public Boolean areSimilar(List<? extends EObject> elements1, List<? extends EObject> elements2);

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
	
	public Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition);
	
	public boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2);
}