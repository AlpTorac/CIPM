package org.splevo.jamopp.diffing.similarity.handlers;

import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.PackageNormalizationRequest;

public class PackageNormalizationHandler implements ISimilarityRequestHandler {
	private Map<Pattern, String> packageNormalizations;
	
	public PackageNormalizationHandler(Map<Pattern, String> packageNormalizations) {
		this.packageNormalizations = packageNormalizations;
	}
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		PackageNormalizationRequest castedR = (PackageNormalizationRequest) req;
		
		return NormalizationUtil.normalize((String) castedR.getParams(), this.packageNormalizations);
	}
}
