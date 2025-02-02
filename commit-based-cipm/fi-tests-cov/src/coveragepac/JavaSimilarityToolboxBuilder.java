package coveragepac;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;



















/**
 * Concrete implementation of {@link AbstractSimilarityToolboxBuilder} for
 * constructing {@link ISimilarityToolbox} instances for computing similarity of
 * Java model elements.
 * 
 * @author Alp Torac Genc
 */
public class JavaSimilarityToolboxBuilder extends AbstractSimilarityToolboxBuilder {
	@Override
	public JavaSimilarityToolboxBuilder instantiate() {
		return (JavaSimilarityToolboxBuilder) super.instantiate();
	}

	@Override
	public JavaSimilarityToolboxBuilder buildRequestHandlerPair(Class<? extends ISimilarityRequest> req,
			ISimilarityRequestHandler srh) {
		return (JavaSimilarityToolboxBuilder) super.buildRequestHandlerPair(req, srh);
	}

	/**
	 * Adds the handlers needed to handle normalisation related
	 * {@link ISimilarityRequest} instances. Passes the given parameters to their
	 * corresponding handlers.
	 * 
	 * @return this
	 * @see {@link NormalizationUtil}
	 */
	public JavaSimilarityToolboxBuilder buildNormalizationHandlers(Map<Pattern, String> classifierNormalizations,
			Map<Pattern, String> compilationUnitNormalizations, Map<Pattern, String> packageNormalizations) {

		this.buildRequestHandlerPair(ClassifierNormalizationRequest.class,
				new ClassifierNormalizationHandler(classifierNormalizations));
		this.buildRequestHandlerPair(CompilationUnitNormalizationRequest.class,
				new CompilationUnitNormalizationHandler(compilationUnitNormalizations));
		this.buildRequestHandlerPair(PackageNormalizationRequest.class,
				new PackageNormalizationHandler(packageNormalizations));
		this.buildRequestHandlerPair(NamespaceNormalizationRequest.class,
				new NamespaceNormalizationHandler(packageNormalizations));

		return this;
	}

	/**
	 * Adds the handlers needed to handle normalisation related
	 * {@link ISimilarityRequest} instances. Does so without any provided
	 * normalisation parameters.
	 * 
	 * @return this
	 * @see {@link #buildNormalizationHandlers(Map, Map, Map)}
	 */
	public JavaSimilarityToolboxBuilder buildNormalizationHandlers() {
		var classifierNormalizations = new LinkedHashMap<Pattern, String>();
		var compilationUnitNormalizations = new LinkedHashMap<Pattern, String>();
		var packageNormalizations = new LinkedHashMap<Pattern, String>();

		return this.buildNormalizationHandlers(classifierNormalizations, compilationUnitNormalizations,
				packageNormalizations);
	}

	/**
	 * Adds the handlers required to handle similarity checking related
	 * {@link ISimilarityRequest} instances.
	 * 
	 * @return this
	 */
	public JavaSimilarityToolboxBuilder buildComparisonHandlers() {
		this.buildRequestHandlerPair(SingleSimilarityCheckRequest.class, new SingleSimilarityCheckHandler());
		this.buildRequestHandlerPair(MultipleSimilarityCheckRequest.class,
				new MultipleSimilarityCheckHandler(this.getCurrentToolbox()));

		return this;
	}

	/**
	 * Adds the handler needed to handle {@link ISimilarityRequest} instances, which
	 * request new similarity switch instances.
	 * 
	 * @return this
	 * @see {@link IJavaSimilaritySwitch}
	 */
	public JavaSimilarityToolboxBuilder buildNewSimilaritySwitchHandler() {
		this.buildRequestHandlerPair(NewSimilaritySwitchRequest.class,
				new NewSimilaritySwitchHandler(this.getCurrentToolbox()));

		return this;
	}
}
