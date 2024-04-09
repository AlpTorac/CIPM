package org.splevo.jamopp.diffing.similarity.requests;

import org.eclipse.emf.ecore.EObject;

public class SingleSimilarityCheckRequest implements ISimilarityRequest {
	private EObject element1;
	private EObject element2;
	
	public SingleSimilarityCheckRequest(EObject element1, EObject element2) {
		this.element1 = element1;
		this.element2 = element2;
	}
	
	/**
	 * @return {{@link #element1}, {@link #element2}, {@link #checkStatementPosition}}
	 */
	@Override
	public EObject[] getParams() {
		return new EObject[] {this.element1, this.element2};
	}
}
