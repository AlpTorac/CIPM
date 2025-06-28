package cipm.consistency.fitests.repositorytests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;

import cipm.consistency.fitests.similarity.jamopp.parser.AbstractJaMoPPParserSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.CombinationTestGenerationStrategy;
import cipm.consistency.fitests.similarity.jamopp.parser.EAllContentSimilarityTestFactory;
import cipm.consistency.fitests.similarity.jamopp.parser.IJaMoPPParserTestGenerationStrategy;

public class MinimalRepoTest extends AbstractJaMoPPParserRepoTest {
	private static final List<String> commitIDs = List.of("364155e24b21122e980cad73da2d0b09c1c994aa",
			"9eda56eaa52c2d5e47aade7a688e9ebda2645bde", "21ae1643905898a29470157de076942a7be05698",
			"9ab81979f64539442b9dc5857b65d696918e4732", "1ae42af88d2702a44bd5192557e8561c18960c58",
			"b9f1ef79249831958732241680ee1ba12dcda078");

	@Override
	protected List<String> getCommitIDs() {
		return commitIDs;
	}

	@Override
	protected URI getRepoURI() {
		return URI.createFileURI("C:\\Users\\sdq-l\\OneDrive\\Desktop\\parserTestRepo");
	}

	@Override
	protected Collection<IJaMoPPParserTestGenerationStrategy> getTestGenerationStrategies() {
		var strats = new ArrayList<IJaMoPPParserTestGenerationStrategy>();
		strats.add(new CombinationTestGenerationStrategy());
		return strats;
	}

	@Override
	protected Collection<AbstractJaMoPPParserSimilarityTestFactory> getTestFactories() {
		var res = new ArrayList<AbstractJaMoPPParserSimilarityTestFactory>();
		res.add(new EAllContentSimilarityTestFactory(this.getSCC()));
		res.forEach((tf) -> tf.setExpectedSimilarityResultProvider(getExpectedSimilarityResultProviderForCommits()));
		return res;
	}
}
