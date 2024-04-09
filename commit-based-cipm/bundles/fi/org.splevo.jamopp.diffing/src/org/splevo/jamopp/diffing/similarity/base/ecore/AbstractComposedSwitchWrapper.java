package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;

public abstract class AbstractComposedSwitchWrapper extends ComposedSwitch<Boolean> implements IComposedSwitchWrapper {
    /** The object to compare the switched element with. */
    private EObject compareElement = null;
    
    public AbstractComposedSwitchWrapper() {
    	
    }
    
    protected AbstractComposedSwitchWrapper(Collection<Switch<Boolean>> switches) {
    	switches.forEach((s) -> this.addSwitch(s));
    }
    
    protected AbstractComposedSwitchWrapper(Switch<Boolean>[] switches) {
    	for (var s : switches) {
    		this.addSwitch(s);
    	}
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
}
