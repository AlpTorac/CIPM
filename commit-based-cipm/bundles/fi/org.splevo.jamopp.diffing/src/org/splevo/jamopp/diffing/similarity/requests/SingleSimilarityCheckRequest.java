package org.splevo.jamopp.diffing.similarity.requests;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class SingleSimilarityCheckRequest implements ISimilarityRequest {
	private EObject element1;
	private EObject element2;
	private IJavaSimilaritySwitch ss;
	
	public SingleSimilarityCheckRequest(EObject element1, EObject element2, IJavaSimilaritySwitch ss) {
		this.element1 = element1;
		this.element2 = element2;
		this.ss = ss;
	}
	
	/**
	 * @return {{@link #element1}, {@link #element2}, {@link #checkStatementPosition} (as {@link Boolean})}
	 */
	@Override
	public Object[] getParams() {
		return new Object[] {this.element1, this.element2, this.ss};
	}
}
