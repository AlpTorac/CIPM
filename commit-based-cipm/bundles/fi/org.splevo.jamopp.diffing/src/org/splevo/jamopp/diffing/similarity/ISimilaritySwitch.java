package org.splevo.jamopp.diffing.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;

public interface ISimilaritySwitch {
	public ISimilarityChecker getSimilarityChecker();

	public EObject getCompareElement();

	public Boolean compare(EObject eo1, EObject eo2);
	
	public default boolean shouldCheckStatementPosition() {
		return this.getSimilarityChecker().checksStatementPositionOnDefault();
	}
	
	public default Boolean areSimilar(List<? extends EObject> elements1, List<? extends EObject> elements2) {
		return this.getSimilarityChecker().areSimilar(elements1, elements2);
	}

	public default Boolean isSimilar(EObject element1, EObject element2) {
		return this.getSimilarityChecker().isSimilar(element1, element2);
	}

	public default Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {
		return this.getSimilarityChecker().isSimilar(element1, element2, checkStatementPosition);
	}
	
	public default boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
		return this.getSimilarityChecker().compareNamespacesByPart(ele1, ele2);
	}
	
    public default String normalizeCompilationUnit(String original) {
    	return this.getSimilarityChecker().normalizeCompilationUnit(original);
    }
    
    public default String normalizePackage(String original) {
    	return this.getSimilarityChecker().normalizePackage(original);
    }
    
    public default String normalizeClassifier(String original) {
    	return this.getSimilarityChecker().normalizeClassifier(original);
    }
    
    public default String normalizeNamespace(String namespace) {
    	return this.getSimilarityChecker().normalizeNamespace(namespace);
    }
}