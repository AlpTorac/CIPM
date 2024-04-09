package org.splevo.jamopp.diffing.similarity.requests;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.splevo.jamopp.diffing.similarity.IJavaSimilaritySwitch;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class MultipleSimilarityCheckRequest implements ISimilarityRequest {
	private Collection<? extends EObject> elements1;
	private Collection<? extends EObject> elements2;
	private Collection<? extends IJavaSimilaritySwitch> sss;
	
	public MultipleSimilarityCheckRequest(
			Collection<? extends EObject> elements1,
			Collection<? extends EObject> elements2,
			Collection<? extends IJavaSimilaritySwitch> sss
			) {
		this.elements1 = elements1;
		this.elements2 = elements2;
	}
	
	/**
	 * @return {{@link #elements1}, {@link #elements2}, {@link #sss}}
	 */
	@Override
	public Object[] getParams() {
		return new Object[] {
				this.elements1,
			 	this.elements2,
			 	this.sss
			};
	}
}
