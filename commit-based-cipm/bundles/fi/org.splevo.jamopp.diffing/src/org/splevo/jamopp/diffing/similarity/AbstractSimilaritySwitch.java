package org.splevo.jamopp.diffing.similarity;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.emftext.language.java.commons.NamespaceAwareElement;

public abstract class AbstractSimilaritySwitch extends ComposedSwitch<Boolean> implements ISimilaritySwitch {
	private ISimilarityChecker sc;
	
    private boolean defaultCheckStatementPosition = true;
    
    /** The object to compare the switched element with. */
    private EObject compareElement = null;
    
    public AbstractSimilaritySwitch(ISimilarityChecker sc, boolean defaultCheckStatementPosition) {
    	this.sc = sc;
    	this.defaultCheckStatementPosition = defaultCheckStatementPosition;
    }

    public boolean getDefaultCheckStatementPosition() {
		return defaultCheckStatementPosition;
	}
    
    public void setDefaultCheckStatementPosition(boolean defaultCheckStatementPosition) {
    	this.defaultCheckStatementPosition = defaultCheckStatementPosition;
    }
    
	@Override
	public EObject getCompareElement() {
		return this.compareElement;
	}
    
	@Override
    public Boolean compare(EObject eo1, EObject eo2) {
    	this.compareElement = eo2;
    	return this.doSwitch(eo1);
    }
    
    /**
     * The default case for not explicitly handled elements always returns null to identify the open
     * decision.
     * 
     * @param object
     *            The object to compare with the compare element.
     * @return null
     */
    @Override
    public Boolean defaultCase(EObject object) {
        return null;
    }
    
    /**
     * Compares the namespaces of two elements by comparing each part of the namespaces.
     * 
     * @param ele1 the first element.
     * @param ele2 the second element to compare to the first element.
     * @return true if the number of parts of the namespaces and each part in both namespaces are equal. false otherwise.
     */
    public boolean compareNamespacesByPart(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
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
    
	@Override
	public ISimilarityChecker getSimilarityChecker() {
		return this.sc;
	}
    
    protected abstract ISimilaritySwitch makeSwitch(boolean checkStatementPosition);
}
