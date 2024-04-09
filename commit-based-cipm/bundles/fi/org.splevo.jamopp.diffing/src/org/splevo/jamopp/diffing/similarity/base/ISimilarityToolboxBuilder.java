package org.splevo.jamopp.diffing.similarity.base;

public interface ISimilarityToolboxBuilder {

	public void setSimilarityToolboxFactory(ISimilarityToolboxFactory stf);

	public ISimilarityToolboxFactory getToolboxFactory();

	public ISimilarityToolbox build();

	public ISimilarityToolboxBuilder instantiate();

	public ISimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req,
			ISimilarityRequestHandler srh);
}