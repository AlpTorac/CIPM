package org.splevo.jamopp.diffing.similarity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.splevo.jamopp.diffing.similarity.base.AbstractSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequest;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityRequestHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.MultipleSimilarityCheckHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.MultipleSimilarityCheckRequest;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckHandler;
import org.splevo.jamopp.diffing.similarity.base.ecore.SingleSimilarityCheckRequest;
import org.splevo.jamopp.diffing.similarity.handlers.ClassifierNormalizationHandler;
import org.splevo.jamopp.diffing.similarity.handlers.CompilationUnitNormalizationHandler;
import org.splevo.jamopp.diffing.similarity.handlers.NamespaceCheckHandler;
import org.splevo.jamopp.diffing.similarity.handlers.NamespaceNormalizationHandler;
import org.splevo.jamopp.diffing.similarity.handlers.NewSimilaritySwitchHandler;
import org.splevo.jamopp.diffing.similarity.handlers.PackageNormalizationHandler;
import org.splevo.jamopp.diffing.similarity.requests.ClassifierNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.NamespaceCheckRequest;
import org.splevo.jamopp.diffing.similarity.requests.CompilationUnitNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.NamespaceNormalizationRequest;
import org.splevo.jamopp.diffing.similarity.requests.NewSimilaritySwitchRequest;
import org.splevo.jamopp.diffing.similarity.requests.PackageNormalizationRequest;

public class JavaSimilarityToolboxBuilder extends AbstractSimilarityToolboxBuilder {
	@Override
	public JavaSimilarityToolboxBuilder instantiate() {
		return (JavaSimilarityToolboxBuilder) super.instantiate();
	}

	@Override
	public JavaSimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req, ISimilarityRequestHandler srh) {
		return (JavaSimilarityToolboxBuilder) super.buildRequestHandlerPair(req, srh);
	}
	
	public JavaSimilarityToolboxBuilder buildNormalizationHandlers(
			Map<Pattern, String> classifierNormalizations,
			Map<Pattern, String> compilationUnitNormalizations,
			Map<Pattern, String> packageNormalizations) {
		
		this.buildRequestHandlerPair(ClassifierNormalizationRequest.class, new ClassifierNormalizationHandler(classifierNormalizations));
		this.buildRequestHandlerPair(CompilationUnitNormalizationRequest.class, new CompilationUnitNormalizationHandler(compilationUnitNormalizations));
		this.buildRequestHandlerPair(PackageNormalizationRequest.class, new PackageNormalizationHandler(packageNormalizations));
		this.buildRequestHandlerPair(NamespaceNormalizationRequest.class, new NamespaceNormalizationHandler(packageNormalizations));
		
		return this;
	}
	
	public JavaSimilarityToolboxBuilder buildNormalizationHandlers() {
		var classifierNormalizations = new LinkedHashMap<Pattern, String>();
		var compilationUnitNormalizations = new LinkedHashMap<Pattern, String>();
		var packageNormalizations = new LinkedHashMap<Pattern, String>();
		
		return this.buildNormalizationHandlers(
				classifierNormalizations,
				compilationUnitNormalizations,
				packageNormalizations);
	}
	
	public JavaSimilarityToolboxBuilder buildComparisonHandlers() {
		this.buildRequestHandlerPair(SingleSimilarityCheckRequest.class, new SingleSimilarityCheckHandler());
		this.buildRequestHandlerPair(MultipleSimilarityCheckRequest.class, new MultipleSimilarityCheckHandler(this.getCurrentToolbox()));
		this.buildRequestHandlerPair(NamespaceCheckRequest.class, new NamespaceCheckHandler());
		
		return this;
	}
	
	public JavaSimilarityToolboxBuilder buildNewSimilaritySwitchHandler() {
		this.buildRequestHandlerPair(NewSimilaritySwitchRequest.class, new NewSimilaritySwitchHandler(this.getCurrentToolbox()));
		
		return this;
	}
}
