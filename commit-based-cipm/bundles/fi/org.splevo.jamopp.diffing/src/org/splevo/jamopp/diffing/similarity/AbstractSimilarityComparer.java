package org.splevo.jamopp.diffing.similarity;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public abstract class AbstractSimilarityComparer implements ISimilarityComparer {
    /**
     * Flag if the position of statement elements should be considered or not.
     */
    private boolean defaultCheckStatementPositionFlag = true;
	
    @Override
	public boolean checksStatementPositionOnDefault() {
    	return this.defaultCheckStatementPositionFlag;
    }

	protected void setChecksStatementPositionOnDefault(boolean defaultCheckStatementPositionFlag) {
    	this.defaultCheckStatementPositionFlag = defaultCheckStatementPositionFlag;
    }
	
	@Override
	public Boolean areSimilar(final List<? extends EObject> elements1, final List<? extends EObject> elements2) {
        if (elements1.size() != elements2.size()) {
            return Boolean.FALSE;
        }
        for (int i = 0; i < elements1.size(); i++) {
            Boolean childSimilarity = isSimilar(elements1.get(i), elements2.get(i));
            if (childSimilarity == Boolean.FALSE) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
	
	@Override
	public Boolean isSimilar(EObject element1, EObject element2) {
		return this.isSimilar(element1, element2, this.checksStatementPositionOnDefault());
	}
	
	@Override
	public Boolean isSimilar(EObject element1, EObject element2, boolean checkStatementPosition) {

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

        // check type specific similarity
        return this.checkSimilarityForResolvedAndSameType(element1, element2, checkStatementPosition);
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
	public abstract ISimilarityComparer clone(boolean checkStatementPosition);
	
	@Override
	public abstract Boolean checkSimilarityForResolvedAndSameType(EObject element1,
			EObject element2,
			boolean checkStatementPosition);
}
