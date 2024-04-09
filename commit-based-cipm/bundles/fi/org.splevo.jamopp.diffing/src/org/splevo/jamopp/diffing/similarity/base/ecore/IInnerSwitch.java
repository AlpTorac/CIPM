package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public interface IInnerSwitch extends ISimilarityRequestHandler {
	public ISimilarityRequestHandler getSimilarityRequestHandler();
	public IComposedSwitchWrapper getContainingSwitch();
	
	public default EObject getCompareElement() {
		return this.getContainingSwitch().getCompareElement();
	}
	
	@Override
	public default Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityRequestHandler().handleSimilarityRequest(req);
	}
}
