package org.splevo.jamopp.diffing.similarity.handlers;

import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.NamespaceNormalizationRequest;

public class NamespaceNormalizationHandler implements ISimilarityRequestHandler {
	private Map<Pattern, String> packageNormalizations;
	
	public NamespaceNormalizationHandler(Map<Pattern, String> packageNormalizations) {
		this.packageNormalizations = packageNormalizations;
	}
	
	@Override
	public String handleSimilarityRequest(ISimilarityRequest req) {
		NamespaceNormalizationRequest castedR = (NamespaceNormalizationRequest) req;
		
		return NormalizationUtil.normalize(castedR.getParams(), this.packageNormalizations);
	}
}
