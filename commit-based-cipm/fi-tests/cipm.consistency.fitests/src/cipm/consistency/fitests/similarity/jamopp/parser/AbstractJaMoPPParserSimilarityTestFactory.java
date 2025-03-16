package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.DynamicNode;

public abstract class AbstractJaMoPPParserSimilarityTestFactory {
	public abstract String getTestDescription();

	public abstract DynamicNode createTestsFor(Resource res1, Path path1, Resource res2, Path path2);
}
