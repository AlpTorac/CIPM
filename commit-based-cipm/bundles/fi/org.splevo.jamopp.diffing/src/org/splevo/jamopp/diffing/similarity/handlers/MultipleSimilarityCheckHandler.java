package org.splevo.jamopp.diffing.similarity.handlers;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.requests.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.requests.MultipleSimilarityCheckRequest;
import org.splevo.jamopp.diffing.similarity.requests.SingleSimilarityCheckRequest;

public class MultipleSimilarityCheckHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public MultipleSimilarityCheckHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	@Override
	public Boolean handleSimilarityRequest(ISimilarityRequest req) {
		MultipleSimilarityCheckRequest castedR = (MultipleSimilarityCheckRequest) req;
		var sides = castedR.getParams();
		var elements1 = sides[0];
		var elements2 = sides[1];
		
        if (elements1.size() != elements2.size()) {
            return Boolean.FALSE;
        }
        
        // Do not iterate so that potential modifications do not cause problems
        var es1 = elements1.toArray(EObject[]::new);
        var es2 = elements2.toArray(EObject[]::new);
        
        for (int i = 0; i < es1.length; i++) {
            Boolean childSimilarity = (Boolean) this.srh.handleSimilarityRequest(
            		new SingleSimilarityCheckRequest(es1[i], es2[i]));
            
            if (childSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
	}
}
