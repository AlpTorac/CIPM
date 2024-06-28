package cipm.consistency.fitests.similarity.java;

import org.splevo.jamopp.diffing.similarity.JavaSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.JavaSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.ISimilarityChecker;
import org.splevo.jamopp.diffing.similarity.base.MapSimilarityToolboxFactory;

/**
 * A concrete implementation of {@link ISimilarityCheckerProvider} that creates
 * a {@link JavaSimilarityChecker}.
 * 
 * @author atora
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
