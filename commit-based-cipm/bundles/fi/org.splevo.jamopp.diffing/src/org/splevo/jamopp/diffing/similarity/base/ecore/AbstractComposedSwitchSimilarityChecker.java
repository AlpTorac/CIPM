package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

public abstract class AbstractComposedSwitchSimilarityChecker extends AbstractSimilarityChecker {
	public AbstractComposedSwitchSimilarityChecker(ISimilarityToolbox st) {
		super(st);
	}

    protected IComposedSwitchWrapper createDefaultNewSwitch() {
    	return (IComposedSwitchWrapper) this.handleSimilarityRequest(this.makeDefaultSwitchRequest());
    }
	
	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		return (Boolean) this.handleSimilarityRequest(new SingleSimilarityCheckRequest((EObject) element1, (EObject) element2, this.createDefaultNewSwitch()));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2) {
		Collection<IComposedSwitchWrapper> sss = new ArrayList<IComposedSwitchWrapper>();
		
		for (int i = 0; i < elements1.size(); i++) {
			sss.add(this.createDefaultNewSwitch());
		}
		
		return (Boolean) this.handleSimilarityRequest(new MultipleSimilarityCheckRequest((Collection<? extends EObject>) elements1, (Collection<? extends EObject>) elements2, sss));
	}

	protected abstract ISimilarityRequest makeDefaultSwitchRequest();
}
