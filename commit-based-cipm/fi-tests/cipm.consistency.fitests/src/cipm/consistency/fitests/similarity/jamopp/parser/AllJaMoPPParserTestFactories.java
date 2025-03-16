package cipm.consistency.fitests.similarity.jamopp.parser;

import java.util.ArrayList;
import java.util.Collection;

import cipm.consistency.fitests.similarity.ISimilarityCheckerContainer;

public class AllJaMoPPParserTestFactories {
	public Collection<AbstractJaMoPPParserSimilarityTestFactory> createFactoriesFor(ISimilarityCheckerContainer scc,
			String resourceFileExtension, boolean contentOrderMatters) {
		var result = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		result.add(new EAllContentSimilarityTestFactory(scc));
		result.add(new RecursiveEAllContentSimilarityTestFactory(scc));
		result.add(new ModelComparisonTestFactory(scc, resourceFileExtension, contentOrderMatters));
		return result;
	}
}
