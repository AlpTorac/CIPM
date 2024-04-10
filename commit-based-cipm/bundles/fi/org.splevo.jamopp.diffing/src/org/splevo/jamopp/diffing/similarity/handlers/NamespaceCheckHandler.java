package org.splevo.jamopp.diffing.similarity.handlers;

import org.splevo.jamopp.diffing.similarity.requests.NamespaceCheckRequest;
import org.emftext.language.java.commons.NamespaceAwareElement;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class NamespaceCheckHandler implements ISimilarityRequestHandler {

	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		NamespaceCheckRequest castedR = (NamespaceCheckRequest) req;
		NamespaceAwareElement[] params = (NamespaceAwareElement[]) castedR.getParams();
		var ele1 = params[0];
		var ele2 = params[1];
		
    	if (ele1.getNamespaces().size() != ele2.getNamespaces().size()) {
    		return false;
    	}
    	for (int idx = 0; idx < ele1.getNamespaces().size(); idx++) {
    		if (!ele1.getNamespaces().get(idx).equals(ele2.getNamespaces().get(idx))) {
    			return false;
    		}
    	}
    	return true;
	}
}
