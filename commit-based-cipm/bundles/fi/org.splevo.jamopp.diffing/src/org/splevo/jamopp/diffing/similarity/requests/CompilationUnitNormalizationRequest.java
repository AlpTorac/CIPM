package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest}, which contains the name of a
 * {@link CompilationUnit} instance: {@code compUnit.name()}.
 * 
 * @author atora
 *
 */
public class CompilationUnitNormalizationRequest implements ISimilarityRequest {
	/**
	 * The name of the {@link CompilationUnit}.
	 */
	private String toBeNormalized;

	/**
	 * Constructs an instance.
	 * 
	 * @param toBeNormalized The name of the {@link CompilationUnit}.
	 */
	public CompilationUnitNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The name of the {@link CompilationUnit}.
	 */
	@Override
	public Object getParams() {
		return this.toBeNormalized;
	}
}