package cipm.consistency.commitintegration.diff.util;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

import org.splevo.diffing.match.HierarchicalMatchEngineFactory;
import org.splevo.jamopp.diffing.similarity.JavaSimilarityChecker;
import org.splevo.jamopp.diffing.similarity.JavaSimilarityToolboxBuilder;
import org.splevo.jamopp.diffing.similarity.base.MapSimilarityToolboxFactory;

/**
 * A generator for HierarchicalMatchEngineFactories specific to Java models.
 * 
 * @author Martin Armbruster
 */
public final class JavaMatchEngineFactoryGenerator {
	private JavaMatchEngineFactoryGenerator() {
	}

	/**
	 * Generates the HierarchicalMatchEngineFactory.
	 * 
	 * @return the generated factory.
	 */
	public static HierarchicalMatchEngineFactory generateMatchEngineFactory() {
        var builder = new JavaSimilarityToolboxBuilder();
        builder.setSimilarityToolboxFactory(new MapSimilarityToolboxFactory());
        
        var toolbox = builder.instantiate()
        	.buildNewSimilaritySwitchHandler()
        	.buildNormalizationHandlers(
        			new LinkedHashMap<Pattern, String>(),
        			new LinkedHashMap<Pattern, String>(),
        			new LinkedHashMap<Pattern, String>()
        		)
        	.buildComparisonHandlers()
        	.build();
		
		return HierarchicalMatchEngineFactoryGenerator.generateMatchEngineFactory(new JavaSimilarityChecker(toolbox), "javaxmi");
	}
}
