package org.splevo.jamopp.diffing.similarity.base.ecore;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityToolbox;

/**
 * A version of {@link AbstractSimilarityChecker}, which is adapted for {@link EObject}.
 * 
 * @author atora
 */
public abstract class AbstractComposedSwitchSimilarityChecker extends AbstractSimilarityChecker {
	/**
	 * @see {@link AbstractSimilarityChecker#AbstractSimilarityChecker(ISimilarityToolbox)}
	 */
	public AbstractComposedSwitchSimilarityChecker(ISimilarityToolbox st) {
		super(st);
	}

	/**
	 * Creates and returns a new switch.
	 */
    protected IComposedSwitchWrapper createDefaultNewSwitch() {
    	return (IComposedSwitchWrapper) this.handleSimilarityRequest(this.makeDefaultSwitchRequest());
    }
	
    /**
     * {@inheritDoc}
     */
	@Override
	public Boolean isSimilar(Object element1, Object element2) {
		return (Boolean) this.handleSimilarityRequest(new SingleSimilarityCheckRequest((EObject) element1, (EObject) element2, this.createDefaultNewSwitch()));
	}

    /**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean areSimilar(Collection<Object> elements1, Collection<Object> elements2) {
		Collection<IComposedSwitchWrapper> sss = new ArrayList<IComposedSwitchWrapper>();
		
		for (int i = 0; i < elements1.size(); i++) {
			sss.add(this.createDefaultNewSwitch());
		}
		
		return (Boolean) this.handleSimilarityRequest(new MultipleSimilarityCheckRequest((Collection<? extends EObject>) elements1, (Collection<? extends EObject>) elements2, sss));
	}

	/**
	 * Used by other methods in this and concrete implementors that create switches. This
	 * method can be overridden in the concrete implementors to create different {@link ISimilarityRequest}
	 * instances, which can be used to create different switches ({@link IComposedSwitchWrapper} instances). 
	 * 
	 * @return A {@link ISimilarityRequest} to create a new switch.
	 */
	protected abstract ISimilarityRequest makeDefaultSwitchRequest();
}
