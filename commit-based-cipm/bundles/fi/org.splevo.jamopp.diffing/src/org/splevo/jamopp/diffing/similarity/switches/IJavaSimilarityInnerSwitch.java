package org.splevo.jamopp.diffing.similarity.switches;

import org.eclipse.emf.ecore.EObject;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.ClassifierNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.CompilationUnitNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.NamespaceCheckRequest;
import org.splevo.jamopp.diffing.similarity.requests.NamespaceNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.PackageNormalizationRequest;

public interface IJavaSimilarityInnerSwitch {
	public ISimilarityRequestHandler getSimilarityRequestHandler();
	public IJavaSimilaritySwitch getSimilaritySwitch();
	
	public default EObject getCompareElement() {
		return this.getSimilaritySwitch().getCompareElement();
	}
	
	public default String normalizeClassifier(String origin) {
		return (String) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new ClassifierNormalizationRequest(origin));
	}
	
	public default String normalizeCompilationUnit(String origin) {
		return (String) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new CompilationUnitNormalizationRequest(origin));
	}
	
	public default String normalizePackage(String origin) {
		return (String) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new PackageNormalizationRequest(origin));
	}
	
	public default String normalizeNamespace(String origin) {
		return (String) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new NamespaceNormalizationRequest(origin));
	}
	
	public default Boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
		return (Boolean) this.getSimilarityRequestHandler()
				.handleSimilarityRequest(new NamespaceCheckRequest(ele1, ele2));
	}
}
