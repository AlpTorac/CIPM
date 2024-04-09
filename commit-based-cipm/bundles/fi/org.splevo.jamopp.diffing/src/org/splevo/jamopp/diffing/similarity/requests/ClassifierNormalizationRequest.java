package org.splevo.jamopp.diffing.similarity.requests;

public class ClassifierNormalizationRequest implements ISimilarityRequest {
	private String toBeNormalized;
	
	public ClassifierNormalizationRequest(String toBeNormalized) {
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
