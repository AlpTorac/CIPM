package org.splevo.jamopp.diffing.similarity;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;

public abstract class AbstractSimilaritySwitch extends ComposedSwitch<Boolean> implements ISimilaritySwitch {
	private ISimilarityComparer sc;
    
    /** The object to compare the switched element with. */
    private EObject compareElement = null;
    
    public AbstractSimilaritySwitch(ISimilarityComparer sc) {
    	this.sc = sc;
    	
    	this.initInnerSwitches();
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
    
	@Override
	public ISimilarityComparer getSimilarityComparer() {
		return this.sc;
	}
	
    protected abstract void initInnerSwitches();
}
