package org.splevo.jamopp.diffing.similarity.requests;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;

public class MultipleSimilarityCheckRequest implements ISimilarityRequest {
	private Collection<? extends EObject> elements1;
	private Collection<? extends EObject> elements2;
	
	public MultipleSimilarityCheckRequest(Collection<? extends EObject> elements1, Collection<? extends EObject> elements2) {
		this.elements1 = elements1;
		this.elements2 = elements2;
	}
	
	/**
	 * @return {{@link #elements1}, {@link #elements2}}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<EObject>[] getParams() {
		return (Collection<EObject>[]) new Object[] {
				(Collection<EObject>) this.elements1,
			 	(Collection<EObject>) this.elements2
			};
	}
}
