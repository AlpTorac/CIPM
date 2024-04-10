package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class NamespaceNormalizationRequest implements ISimilarityRequest {
	private String toBeNormalized;
	
	public NamespaceNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}
	
	/**
	 * @return {@link #toBeNormalized}
	 */
	@Override
	public Object getParams() {
		return this.toBeNormalized;
	}
}
