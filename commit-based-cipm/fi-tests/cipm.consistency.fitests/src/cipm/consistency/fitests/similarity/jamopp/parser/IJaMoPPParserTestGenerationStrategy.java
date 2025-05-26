package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;

public interface IJaMoPPParserTestGenerationStrategy {
	public Collection<DynamicNode> createTests(Path[] pathArr, Resource[] resArr,
			Collection<AbstractJaMoPPParserSimilarityTestFactory> tFacs);

	public String getTestGenerationStrategyDescription();
}
