package org.splevo.jamopp.diffing.similarity.requests;

import org.emftext.language.java.commons.NamespaceAwareElement;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class NamespaceCheckRequest implements ISimilarityRequest {
	private NamespaceAwareElement ele1;
	private NamespaceAwareElement ele2;
	
	public NamespaceCheckRequest(NamespaceAwareElement ele1, NamespaceAwareElement ele2) {
		this.ele1 = ele1;
		this.ele2 = ele2;
	}
	
	/**
	 * @return {{@link #ele1}, {@link #ele2}}
	 */
	@Override
	public NamespaceAwareElement[] getParams() {
		return new NamespaceAwareElement[] {this.ele1, this.ele2};
	}
}
