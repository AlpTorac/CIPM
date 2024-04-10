package org.splevo.jamopp.diffing.similarity.base.ecore;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class SingleSimilarityCheckRequest implements ISimilarityRequest {
	private EObject element1;
	private EObject element2;
	private IComposedSwitchWrapper ss;
	
	public SingleSimilarityCheckRequest(EObject element1, EObject element2, IComposedSwitchWrapper ss) {
		this.element1 = element1;
		this.element2 = element2;
		this.ss = ss;
	}
	
	/**
	 * @return {{@link #element1}, {@link #element2}, {@link #ss}}
	 */
	@Override
	public Object getParams() {
		return new Object[] {this.element1, this.element2, this.ss};
	}
}
