package org.splevo.jamopp.diffing.similarity.requests;

import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;

/**
 * An {@link ISimilarityRequest}, which contains the qualified name of a
 * {@link ConcreteClassifier} instance: {@code conCls.getQualifiedName()}.
 * 
 * @author atora
 */
public class ClassifierNormalizationRequest implements ISimilarityRequest {
	/**
	 * The qualified name of the {@link ConcreteClassifier}.
	 */
	private String toBeNormalized;

	/**
	 * Constructs an instance.
	 * 
	 * @param toBeNormalized The qualified name of the {@link ConcreteClassifier}
	 */
	public ClassifierNormalizationRequest(String toBeNormalized) {
		this.toBeNormalized = toBeNormalized;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return The qualified name of the {@link ConcreteClassifier}
	 */
	@Override
	public Object getParams() {
		return this.toBeNormalized;
	}
}