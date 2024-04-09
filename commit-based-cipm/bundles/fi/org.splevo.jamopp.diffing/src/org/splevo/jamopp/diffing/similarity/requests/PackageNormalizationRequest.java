package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class PackageNormalizationRequest implements ISimilarityRequest {
	private String toBeNormalized;
	
	public PackageNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}
	
	/**
	 * @return {@link #toBeNormalized}
	 */
	@Override
	public String getParams() {
		return this.toBeNormalized;
	}
}
