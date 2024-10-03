package cipm.consistency.fitests.similarity.base;

import org.splevo.jamopp.diffing.similarity.JavaSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.JavaSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.MapSimilarityToolboxFactory;

/**
 * A concrete implementation of {@link ISimilarityCheckerProvider} that creates
 * {@link JavaSimilarityChecker} instances.
 * 
 * @author Alp Torac Genc
 */
public class JavaSimilarityCheckerProvider implements ISimilarityCheckerProvider {
	@Override
	public ISimilarityChecker createSC() {
		var builder = new JavaSimilarityToolboxBuilder();
		builder.setSimilarityToolboxFactory(new MapSimilarityToolboxFactory());

		var toolbox = builder.instantiate().buildNewSimilaritySwitchHandler().buildNormalizationHandlers()
				.buildComparisonHandlers().build();

		return new JavaSimilarityChecker(toolbox);
	}
}
