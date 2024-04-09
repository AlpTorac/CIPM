package org.splevo.jamopp.diffing.similarity.requests;

public class NamespaceNormalizationRequest implements ISimilarityRequest {
	private String toBeNormalized;
	
	public NamespaceNormalizationRequest(String toBeNormalized) {
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
