package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;

public class CombinationTestGenerationStrategy implements IJaMoPPParserTestGenerationStrategy {
	private final static String description = "combination strategy";

	@Override
	public Collection<DynamicNode> createTests(Path[] pathArr, Resource[] resArr,
			Collection<AbstractJaMoPPParserSimilarityTestFactory> tFacs) {
		if (pathArr.length != resArr.length) {
			Assertions.fail("Lengths of path and resource arrays do not match");
		}

		var tests = new ArrayList<DynamicNode>();
		tFacs.forEach((tf) -> {
			var testsForModelDirs = new ArrayList<DynamicNode>();
			for (int i = 0; i < pathArr.length; i++) {
				var path1 = pathArr[i];
				var res1 = resArr[i];
				for (int j = 0; j < pathArr.length; j++) {
					var path2 = pathArr[j];
					var res2 = resArr[j];
					testsForModelDirs.add(tf.createTestsFor(res1, path1, res2, path2));
				}
			}
			tests.add(DynamicContainer.dynamicContainer(
					String.format("%s (with %s)", tf.getTestDescription(), this.getTestGenerationStrategyDescription()),
					testsForModelDirs));
		});

		return tests;
	}

	@Override
	public String getTestGenerationStrategyDescription() {
		return description;
	}
}
