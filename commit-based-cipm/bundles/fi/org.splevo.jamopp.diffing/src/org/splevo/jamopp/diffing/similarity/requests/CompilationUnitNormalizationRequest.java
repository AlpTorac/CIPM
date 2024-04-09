package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

public class CompilationUnitNormalizationRequest implements ISimilarityRequest {
	private String toBeNormalized;
	
	public CompilationUnitNormalizationRequest(String toBeNormalized) {
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
