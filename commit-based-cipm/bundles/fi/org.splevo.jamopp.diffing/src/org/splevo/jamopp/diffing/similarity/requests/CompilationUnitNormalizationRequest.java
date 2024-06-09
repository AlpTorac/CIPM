package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest}, which contains the name of a
 * {@link CompilationUnit} instance: {@code compUnit.name()}.
 * @author atora
 *
 */
public class CompilationUnitNormalizationRequest implements ISimilarityRequest {
	/**
	 * The name of a {@link CompilationUnit}.
	 */
	private String toBeNormalized;
	
	/**
	 * Constructs an instance.
	 * 
	 * @param toBeNormalized {@link #toBeNormalized}
	 */
	public CompilationUnitNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}
	
	/**
	 * {@inheritDoc}
	 * @return {@link #toBeNormalized}
	 */
	@Override
	public Object getParams() {
		return this.toBeNormalized;
	}
}
