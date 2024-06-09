package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest}, which contains the name of a
 * {@link CompilationUnit} instance normalised via
 * {@link CompilationUnitNormalizationRequest} first.
 * 
 * @author atora
 *
 */
public class PackageNormalizationRequest implements ISimilarityRequest {
	/**
	 * The name of a {@link CompilationUnit} instance normalised via
	 * {@link CompilationUnitNormalizationRequest} first.
	 */
	private String toBeNormalized;

	/**
	 * Constructs an instance with the given parameter.
	 * 
	 * @param toBeNormalized {@link #toBeNormalized}
	 */
	public PackageNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return {@link #toBeNormalized}
	 */
	@Override
	public Object getParams() {
		return this.toBeNormalized;
	}
}
