package org.splevo.jamopp.diffing.similarity.handlers;

import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.diffing.util.NormalizationUtil;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.requests.CompilationUnitNormalizationRequest;

public class CompilationUnitNormalizationHandler implements ISimilarityRequestHandler {
	private Map<Pattern, String> compilationUnitNormalizations;
	
	public CompilationUnitNormalizationHandler(Map<Pattern, String> compilationUnitNormalizations) {
		this.compilationUnitNormalizations = compilationUnitNormalizations;
	}
	
	@Override
	public Object handleSimilarityRequest(ISimilarityRequest req) {
		CompilationUnitNormalizationRequest castedR = (CompilationUnitNormalizationRequest) req;
		
		return NormalizationUtil.normalize((String) castedR.getParams(), this.compilationUnitNormalizations);
	}
}
