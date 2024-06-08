package org.splevo.jamopp.diffing.similarity.base;

/**
 * An abstract class for concrete similarity toolbox builders to extend.
 * Complements {@link ISimilarityToolboxBuilder} with the integration of
 * {@link ISimilarityToolboxFactory}.
 * 
 * @author atora
 */
public class AbstractSimilarityToolboxBuilder implements ISimilarityToolboxBuilder {
	private ISimilarityToolboxFactory stf;

	/**
	 * The current version of the {@link ISimilarityToolbox} instance being built.
	 */
	private ISimilarityToolbox st;

	public AbstractSimilarityToolboxBuilder() {
		super();
	}

	@Override
	public void setSimilarityToolboxFactory(ISimilarityToolboxFactory stf) {
		this.stf = stf;
	}

	@Override
	public ISimilarityToolboxFactory getToolboxFactory() {
		return this.stf;
	}

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

	@Override
	public ISimilarityToolboxBuilder instantiate() {
		this.st = this.getToolboxFactory().createSimilarityToolbox();
		return this;
	}

	@Override
	public ISimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req,
			ISimilarityRequestHandler srh) {
		this.getCurrentToolbox().addRequestHandlerPair(req, srh);
		return this;
	}

}