package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.Collection;

import org.eclipse.emf.ecore.util.Switch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;

public abstract class AbstractComposedSimilaritySwitch extends AbstractComposedSwitchWrapper
		implements ISimilarityRequestHandler {

	private ISimilarityRequestHandler srh;
	
    public AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh) {
    	super();
    	this.srh = srh;
    }
    
    protected AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh, Collection<Switch<Boolean>> switches) {
    	super(switches);
    	this.srh = srh;
    }
    
    protected AbstractComposedSimilaritySwitch(ISimilarityRequestHandler srh, Switch<Boolean>[] switches) {
    	super(switches);
    	this.srh = srh;
    }
    
    protected ISimilarityRequestHandler getSimilarityRequestHandler() {
    	return this.srh;
    }
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		return this.getSimilarityRequestHandler().handleSimilarityRequest(req);
	}

}
