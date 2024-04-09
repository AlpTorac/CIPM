package org.splevo.jamopp.diffing.similarity.handlers;

import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.ClassifierNormalizationRequest;

public class ClassifierNormalizationHandler implements ISimilarityRequestHandler {
	private Map<Pattern, String> classifierNormalizations;
	
	public ClassifierNormalizationHandler(Map<Pattern, String> classifierNormalizations) {
		this.classifierNormalizations = classifierNormalizations;
	}
	
	@Override
	public String handleSimilarityRequest(ISimilarityRequest req) {
		ClassifierNormalizationRequest castedR = (ClassifierNormalizationRequest) req;
		
		return NormalizationUtil.normalize(castedR.getParams(), this.classifierNormalizations);
	}
}
