package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public class MultipleSimilarityCheckHandler implements ISimilarityRequestHandler {
	private ISimilarityRequestHandler srh;
	
	public MultipleSimilarityCheckHandler(ISimilarityRequestHandler srh) {
		this.srh = srh;
	}
	
	/**
	 * Check two object lists if they are similar.
	 *
	 * The elements is compared pairwise and it is the responsibility of the provided list
	 * implementations to return them in an appropriate order by calling get(i) with a increasing
	 * index i.
	 *
	 * @return TRUE, if they are all similar; FALSE if a different number of elements is submitted
	 * or at least one pair of elements is not similar to each other.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean handleSimilarityRequest(ISimilarityRequest req) {
		MultipleSimilarityCheckRequest castedR = (MultipleSimilarityCheckRequest) req;
		
		var params = castedR.getParams();
		Collection<? extends EObject> elements1 = (Collection<? extends EObject>) params[0];
		Collection<? extends EObject> elements2 = (Collection<? extends EObject>) params[1];
		Collection<? extends IComposedSwitchWrapper> sss = (Collection<? extends IComposedSwitchWrapper>) params[2];
		
		int size = elements1.size();
		
        if (size != elements2.size()) {
            return Boolean.FALSE;
        }
        
        if (size != sss.size()) {
        	return null;
        }
        
        var es1 = elements1.toArray(EObject[]::new);
        var es2 = elements2.toArray(EObject[]::new);
        var ssA = sss.toArray(IComposedSwitchWrapper[]::new);
        
        for (int i = 0; i < size; i++) {
            Boolean childSimilarity = (Boolean) this.srh.handleSimilarityRequest(
            		new SingleSimilarityCheckRequest(es1[i], es2[i], ssA[i]));
            
            if (childSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
	}
}
