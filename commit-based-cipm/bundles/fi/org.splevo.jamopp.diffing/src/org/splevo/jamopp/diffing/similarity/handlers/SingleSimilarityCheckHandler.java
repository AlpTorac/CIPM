package org.splevo.jamopp.diffing.similarity.handlers;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.splevo.jamopp.diffing.similarity.requests.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.requests.SingleSimilarityCheckRequest;

public class SingleSimilarityCheckHandler implements ISimilarityRequestHandler {
	
	public Boolean isSimilar(EObject element1, EObject element2) {
        // check that either both or none of them is null
        if (element1 == element2) {
            return Boolean.TRUE;
        }

        if (onlyOneIsNull(element1, element2)) {
            return Boolean.FALSE;
        }

        // if a proxy is present try to resolve it
        // the other element is used as a context.
        // TODO Clarify why it can happen that one proxy is resolved and the other is not
        // further notes available with the issue
        // https://sdqbuild.ipd.kit.edu/jira/browse/SPLEVO-279
        if (element2.eIsProxy() && !element1.eIsProxy()) {
            element2 = EcoreUtil.resolve(element2, element1);
        } else if (element1.eIsProxy() && !element2.eIsProxy()) {
            element1 = EcoreUtil.resolve(element1, element2);
        }

        // check the elements to be of the same type
        if (!element1.getClass().equals(element2.getClass())) {
            return Boolean.FALSE;
        }
        
        return Boolean.TRUE;
	}

    /**
     * Method to check if only one of the provided elements is null.
     *
     * @param element1
     *            The first element.
     * @param element2
     *            The second element.
     * @return True if only one element is null and the other is not.
     */
    protected Boolean onlyOneIsNull(final EObject element1, final EObject element2) {
        Boolean onlyOneIsNull = false;
        if (element1 != null && element2 == null) {
            onlyOneIsNull = Boolean.TRUE;
        } else if (element1 == null && element2 != null) {
            onlyOneIsNull = Boolean.TRUE;
        }
        return onlyOneIsNull;
    }
	
	@Override
	public Boolean handleSimilarityRequest(ISimilarityRequest req) {
		SingleSimilarityCheckRequest castedR = (SingleSimilarityCheckRequest) req;
		var params = castedR.getParams();
		EObject elem1 = (EObject) params[0];
		EObject elem2 = (EObject) params[1];
		
		return this.isSimilar(elem1, elem2);
	}
}
