package org.splevo.jamopp.diffing.similarity.switches;

import org.emftext.language.java.commons.NamedElement;
import org.emftext.language.java.commons.util.CommonsSwitch;
import org.splevo.jamopp.diffing.similarity.SimilaritySwitch;

/**
 * Similarity decisions for commons elements.
 */
public class CommonsSimilaritySwitch extends CommonsSwitch<Boolean> {
	private final SimilaritySwitch similaritySwitch;

	/**
	 * @param similaritySwitch
	 */
	public CommonsSimilaritySwitch(SimilaritySwitch similaritySwitch) {
		this.similaritySwitch = similaritySwitch;
	}

	/**
     * Check named element
     * 
     * Similarity is defined by the names of the elements.
     * 
     * @param element1
     *            The method call to compare with the compare element.
     * @return True As null always means null.
     */
    @Override
    public Boolean caseNamedElement(NamedElement element1) {
        NamedElement element2 = (NamedElement) this.similaritySwitch.getCompareElement();

        if (element1.getName() == null) {
            return (element2.getName() == null);
        }

        return (element1.getName().equals(element2.getName()));
    }
}