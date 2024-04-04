package org.splevo.jamopp.diffing.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

public interface ISimilaritySwitch {
	public ISimilarityComparer getSimilarityComparer();

	public EObject getCompareElement();

	public Boolean compare(EObject eo1, EObject eo2);
	
	public default Boolean areSimilar(List<? extends EObject> elements1, List<? extends EObject> elements2) {
		return this.getSimilarityComparer().areSimilar(elements1, elements2);
	}

	public default Boolean isSimilar(EObject element1, EObject element2) {
		return this.getSimilarityComparer().isSimilar(element1, element2);
	}

	public default Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {
		return this.getSimilarityComparer().isSimilar(element1, element2, checkStatementPosition);
	}
}