package org.splevo.jamopp.diffing.similarity.base;

/**
 * An abstract class for concrete similarity toolbox builders to extend.
 * Complements {@link ISimilarityToolboxBuilder} with the integration of
 * {@link ISimilarityToolboxFactory}.
 * 
 * @author atora
 */
public class AbstractSimilarityToolboxBuilder implements ISimilarityToolboxBuilder {
	/**
	 * The {@link ISimilarityToolboxFactory}, which determines the data structure
	 * used by the {@link ISimilarityToolbox} instances built using this builder.
	 */
	private ISimilarityToolboxFactory stf;

	/**
	 * The current version of the {@link ISimilarityToolbox} instance being built.
	 */
	private ISimilarityToolbox st;

	/**
	 * Constructs an instance.
	 */
	public AbstractSimilarityToolboxBuilder() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setSimilarityToolboxFactory(ISimilarityToolboxFactory stf) {
		this.stf = stf;
	}

	/**
	 * @return {@link #stf}
	 */
	@Override
	public ISimilarityToolboxFactory getToolboxFactory() {
		return this.stf;
	}

	/**
	 * @return {@link #st}
	 */
	protected ISimilarityToolbox getCurrentToolbox() {
		return this.st;
	}

	/**
	 * {@inheritDoc} <br>
	 * <br>
	 * <b>Sets {@link #st} to null. </b>
	 */
	@Override
	public ISimilarityToolbox build() {
		var result = this.getCurrentToolbox();
		this.st = null;
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISimilarityToolboxBuilder instantiate() {
		this.st = this.getToolboxFactory().createSimilarityToolbox();
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ISimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req,
			ISimilarityRequestHandler srh) {
		this.getCurrentToolbox().addRequestHandlerPair(req, srh);
		return this;
	}

}