package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;

/**
 * An interface meant for classes that encapsulate the logic on how to iterate
 * through provided paths, resources and
 * {@link AbstractJaMoPPParserSimilarityTestFactory}, while generating dynamic
 * tests.
 * 
 * @author Alp Torac Genc
 */
public interface IJaMoPPParserTestGenerationStrategy {
	/**
	 * Generates dynamic tests for each model directories based on the registered
	 * {@link AbstractJaMoPPParserSimilarityTestFactory} instances. <br>
	 * <br>
	 * See the concrete implementor's documentation for more details.
	 */
	public Collection<DynamicNode> createTests(Path[] pathArr, Resource[] resArr,
			Collection<AbstractJaMoPPParserSimilarityTestFactory> tFacs);

	/**
	 * @return A description for this test generation strategy, which may be added
	 *         to test descriptions.
	 */
	public String getTestGenerationStrategyDescription();
}
