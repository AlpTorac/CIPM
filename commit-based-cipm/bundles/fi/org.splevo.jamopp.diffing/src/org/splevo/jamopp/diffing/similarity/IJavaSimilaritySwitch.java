package org.splevo.jamopp.diffing.similarity;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public interface IJavaSimilaritySwitch extends ISimilarityRequestHandler {
	public EObject getCompareElement();

	public Boolean compare(EObject eo1, EObject eo2);
}