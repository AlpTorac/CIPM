package cipm.consistency.fitests.similarity.jamopp.parser;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;

public class IterativeTestGenerationStrategy implements IJaMoPPParserTestGenerationStrategy {
	private final static String description = "symmetric iteration strategy";

	// TODO Fix and spare actually creating the multi-dimensional array
	
	private Iterator<int[]> getIterator(Path[] pathArr, Resource[] resArr) {
		final int[][] idxs = new int[pathArr.length * 2][2];

		for (int i = 0; i < pathArr.length; i++) {
			idxs[i * 2] = new int[] { i, i + 1 };
			idxs[i * 2 + 1] = new int[] { i + 1, i };
		}

		return new Iterator<int[]>() {
			private int currentIdx = 0;
			private int[][] indices = idxs;

			@Override
			public boolean hasNext() {
				return currentIdx < indices.length && indices[currentIdx] != null;
			}

			@Override
			public int[] next() {
				var result = indices[currentIdx];
				currentIdx++;
				return result;
			}

		};
	}

	@Override
	public Collection<DynamicNode> createTests(Path[] pathArr, Resource[] resArr,
			Collection<AbstractJaMoPPParserSimilarityTestFactory> tFacs) {
		if (pathArr.length != resArr.length) {
			Assertions.fail("Lengths of path and resource arrays do not match");
		}

		var tests = new ArrayList<DynamicNode>();
		tFacs.forEach((tf) -> {
			var testsForModelDirs = new ArrayList<DynamicNode>();
			var it = this.getIterator(pathArr, resArr);
			while (it.hasNext()) {
				var idxs = it.next();
				var path1 = pathArr[idxs[0]];
				var res1 = resArr[idxs[0]];
				var path2 = pathArr[idxs[1]];
				var res2 = resArr[idxs[1]];
				testsForModelDirs.add(tf.createTestsFor(res1, path1, res2, path2));
				testsForModelDirs.add(tf.createTestsFor(res2, path2, res1, path1));
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
