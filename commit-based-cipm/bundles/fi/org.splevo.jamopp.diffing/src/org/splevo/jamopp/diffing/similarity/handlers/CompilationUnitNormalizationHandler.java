package org.splevo.jamopp.diffing.similarity.handlers;

import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.requests.CompilationUnitNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.ISimilarityRequest;

public class CompilationUnitNormalizationHandler implements ISimilarityRequestHandler {
	private Map<Pattern, String> compilationUnitNormalizations;
	
	public CompilationUnitNormalizationHandler(Map<Pattern, String> compilationUnitNormalizations) {
		this.compilationUnitNormalizations = compilationUnitNormalizations;
	}
	
	@Override
	public String handleSimilarityRequest(ISimilarityRequest req) {
		CompilationUnitNormalizationRequest castedR = (CompilationUnitNormalizationRequest) req;
		
		return NormalizationUtil.normalize(castedR.getParams(), this.compilationUnitNormalizations);
	}
}
